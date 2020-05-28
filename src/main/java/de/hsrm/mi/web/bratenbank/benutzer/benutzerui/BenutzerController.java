package de.hsrm.mi.web.bratenbank.benutzer.benutzerui;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerService;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzernameSchonVergeben;

@Controller
public class BenutzerController {
    @Autowired
    BenutzerService benutzerRepository;

    @GetMapping("/benutzer")
    public String getForm(Model m){
        Benutzer b = new Benutzer();
        m.addAttribute("benutzer", b);
        return "benutzerui/benutzerregistrierung";
    }
    
    @PostMapping("/benutzer")
    public String benutzeranlegen(@ModelAttribute("benutzer") @Valid Benutzer bdaten, BindingResult result, Model m) throws BenutzernameSchonVergeben{
      
        if(result.hasErrors()){
            m.addAttribute("benutzer", bdaten);
            return "benutzerui/benutzerregistrierung";
        }
        if(!bdaten.isNutzungsbedingungenok()){
            return "benutzerui/benutzerregistrierung";
        }
        
        if(!result.hasErrors() && bdaten.isNutzungsbedingungenok()){
        
            try{
                benutzerRepository.registriereBenutzer(bdaten);

            }catch(BenutzernameSchonVergeben exc){
                bdaten.setLoginname("");
                m.addAttribute("benutzer", bdaten);
                return "benutzerui/benutzerregistrierung";
            }
        
        }
        return "login";
   

    
    }
}