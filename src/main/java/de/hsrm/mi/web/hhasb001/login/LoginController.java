package de.hsrm.mi.web.hhasb001.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);


    @GetMapping("/login")
    public String backView(){
        return "login";
    }
    @PostMapping("/login")
    public String pruefLog(Model m, @RequestParam("username") String username, @RequestParam("password") String password){
        int laenge = username.length();
        String c =(new StringBuffer(username).append(laenge).toString());
        
        if(password.equals(c)){
            return "redirect:angebote";
        }
        else if(!password.equals(c) && username != ""){
           logger.info("Hinweis: Das korrekte Passwort für {} ist {}", username, c);
           String ausgabe = "Hinweis: Das korrekte Passwort für " +username+ " ist " +c+ ".";
           m.addAttribute("hinweis", ausgabe);
        }
       
        return "login";

    }
}