package de.hsrm.mi.web.bratenbank.bratservice;

import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerRepository;
import de.hsrm.mi.web.bratenbank.bratrepo.Braten;
import de.hsrm.mi.web.bratenbank.bratrepo.BratenRepository;

@Service
public class BratServiceImpl implements BratenService {
    @Autowired BratenRepository br; 
    @Autowired BenutzerRepository benutzerrep; 
    @Autowired SimpMessagingTemplate broker;
    Logger logger = LoggerFactory.getLogger(BratServiceImpl.class);

    @Override
    public List<Braten> alleBraten() {
        // TODO Auto-generated method stub
        //List<Braten> bra = br.findAll();
        return br.findAll();
    }

    @Override
    public Optional<Braten> sucheBratenMitId(int id) {
        // TODO Auto-generated method stub
        //Optional<Braten> ob = br.findById(id);
        return br.findById(id);
    }

    @Transactional
    @Override
    @SendTo("/topic/braten")
    public Braten editBraten(String loginname, Braten braten) {
        BratenMessage bm = new BratenMessage();

         try{
             Benutzer b = benutzerrep.findByLoginname(loginname);
             braten.setAnbieter(b);
             br.save(braten);
             logger.info("Username:" + loginname+ " Gesucht:" +b+"");
             braten.getAnbieter().getAngebote().add(braten);
             bm.operation = "change";
             broker.convertAndSend("/topic/braten", bm.operation);
         }catch(OptimisticLockException ox){
             throw new BratenServiceException();
         }
        return br.getOne(braten.getId());
    }

    @Override
    @SendTo("/topic/braten")
    public void loescheBraten(int bratendatenid) {
        BratenMessage bm = new BratenMessage();
        bm.operation = "delete";
        broker.convertAndSend("/topic/braten", bm.operation);
        br.deleteById(bratendatenid);

    }
    
}