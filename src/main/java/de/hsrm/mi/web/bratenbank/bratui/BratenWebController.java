package de.hsrm.mi.web.bratenbank.bratui;

import java.security.Principal;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.bratenbank.benutzer.BenutzerService;
import de.hsrm.mi.web.bratenbank.bratrepo.Braten;
import de.hsrm.mi.web.bratenbank.bratrepo.BratenRepository;
import de.hsrm.mi.web.bratenbank.bratservice.BratenService;
import de.hsrm.mi.web.bratenbank.bratservice.BratenServiceException;



@Controller
@SessionAttributes(names = {"loggedinusername"})
public class BratenWebController {
    Logger logger = LoggerFactory.getLogger(BratenWebController.class);
    @Autowired BratenRepository br; 
    @Autowired BratenService bs;
    @Autowired BenutzerService benutzerservice;

    @GetMapping("/braten/angebot")
    public String backV(final Model m){       
        m.addAttribute("angebote", bs.alleBraten());
        return "braten/liste";
    }

    @GetMapping("/braten/angebot/neu")
    public String getForm(Model m, Locale locale){
        m.addAttribute("sprache", locale.getLanguage());
        m.addAttribute("angebotform",  new Braten());
        return "braten/bearbeiten";
    }
    
    @PostMapping("/braten/angebot/neu")
    public String angEinf(@ModelAttribute("angebotform") @Valid Braten daten, BindingResult result, Principal prinz, 
        Model m){

        if(result.hasErrors()){
            m.addAttribute("angebotform", daten);
            return "braten/bearbeiten";
        }
        try{
            logger.info("loginname", prinz.getName());
            bs.editBraten(benutzerservice.findeBenutzer(prinz.getName()).getLoginname(), daten);
            
        }catch(BratenServiceException bx){
            return "braten/bearbeiten";
        }
            m.addAttribute("angebote", bs.alleBraten());
            return "redirect:/braten/angebot";
        
        
    }
    @GetMapping("/braten/angebot/{id}")
    public String edit(@PathVariable int id, Optional<Braten> list, Model m){

        if(bs.sucheBratenMitId(id) != null){
            list = bs.sucheBratenMitId(id);
            bs.loescheBraten(id);
            m.addAttribute("angebotform", list);
            m.addAttribute("angebote", bs.alleBraten());
            return "braten/bearbeiten";
        }else{
            return "redirect:/braten/angebot";
        }
        
    }

    @GetMapping("/braten/angebot/{id}/del")
    public String delete(Model m, @PathVariable int id ){
        bs.loescheBraten(id);
        m.addAttribute("angebote", bs.alleBraten());
        return "redirect:/braten/angebot";
    }




 
 
    
    
}