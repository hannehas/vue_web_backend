package de.hsrm.mi.web.bratenbank.bratapi;

import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerService;
import de.hsrm.mi.web.bratenbank.bratrepo.Braten;
import de.hsrm.mi.web.bratenbank.bratservice.BratenService;
import de.hsrm.mi.web.bratenbank.bratservice.BratenServiceException;

@RestController
@RequestMapping("/api")
public class BratenRestApi {
    @Autowired BenutzerService benutzerservice; 
    @Autowired BratenService bratenservice; 

    @GetMapping("/braten")
    public List<Braten> getAllBraten(){
        return bratenservice.alleBraten();
    }
    @GetMapping("/braten/{id}")
    public Optional<Braten> getBratenById(@PathVariable("id") int index){
        Optional<Braten> b = bratenservice.sucheBratenMitId(index);

        if(b.isEmpty()){
            throw new BratenApiException();
        }else{
            return b; 
        }
    }
    @DeleteMapping("/braten/{id}")
    public void delBraten(@PathVariable("id") int index){
        if(bratenservice.sucheBratenMitId(index).isEmpty()){
            throw new BratenApiException();
        }else{
           bratenservice.loescheBraten(index);
        }
    }
    @PostMapping("/braten")
    public Braten postnewBraten(@RequestBody @Valid Braten newBraten, @RequestParam("loginname") String login){
        Benutzer b = benutzerservice.findeBenutzer(login);
        
        if( b!= null){
            bratenservice.editBraten(login, newBraten);
            return bratenservice.sucheBratenMitId(newBraten.getId()).get();

        }else{
            throw new BratenApiException();
        }      

    }
    
}