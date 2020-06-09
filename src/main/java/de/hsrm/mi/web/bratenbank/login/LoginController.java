package de.hsrm.mi.web.bratenbank.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerService;

@Controller
@SessionAttributes(names = {"loggedinusername"})
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired BenutzerService benutzerservice;
    String loggedinusername;


    @GetMapping("/login")
    public String backView(){
        return "login";
    }
    @PostMapping("/login")
    public String pruefLog(Model m, @RequestParam("username") String username, @RequestParam("password") String password){
        
        boolean correct = benutzerservice.pruefeLogin(username, password);
        String c = benutzerservice.ermittlePasswort(username);
        

        if(correct){
            m.addAttribute("loggedinusername", username);
            return "redirect:braten/angebot";
        }
        else{
           logger.info("Hinweis: Das korrekte Passwort für {} ist {}", username, c);
           String ausgabe = "Hinweis: Das korrekte Passwort für " +username+ " ist " +c+ ".";
           m.addAttribute("hinweis", ausgabe);
        }
       
        return "login";

    }
}