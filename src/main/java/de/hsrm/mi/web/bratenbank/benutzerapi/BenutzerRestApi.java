package de.hsrm.mi.web.bratenbank.benutzerapi;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerService;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzernameSchonVergeben;

@RestController
@RequestMapping("/api")
public class BenutzerRestApi {
    @Autowired BenutzerService benutzerservice;

    @PostMapping("/benutzer")
    public Benutzer postNewBenutzer(@RequestBody Benutzer newBenutzer){
       
        try{
             newBenutzer = benutzerservice.registriereBenutzer(newBenutzer);

        }catch(BenutzernameSchonVergeben bn){
            throw new BenutzerApiException();
        }
       return newBenutzer;
    }
    @GetMapping(value = "/benutzer/{loginname}" , produces =MediaType.APPLICATION_JSON_VALUE)
    public Benutzer getBenutzerByName(@PathVariable("loginname") String login){
        Benutzer bn;
        
            bn = benutzerservice.findeBenutzer(login);
            if(bn == null){
              throw new BenutzerApiException();
            }else{
                 return bn;
            }
    }
    
}