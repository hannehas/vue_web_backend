package de.hsrm.mi.web.bratenbank.bratboerse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
//@RequestMapping("/angebot")
@SessionAttributes(names = {"angebote"})
public class BratenAngebotController {
    ArrayList<BratenDaten> listBraten = new ArrayList<BratenDaten>();
    Logger logger = LoggerFactory.getLogger(BratenAngebotController.class);
    
    @ModelAttribute("angebote")
    public void initListe(Model m){

        BratenDaten b1 = new BratenDaten(); 
        b1.setName("Jönhard");
        b1.setAbholort("Vollradisroda");
        b1.setBeschreibung("leckerer Grünkohlbraten");
        b1.setHaltbarbis(LocalDate.of(2020, 05, 20));
        b1.setVgrad(25);

        BratenDaten b2 = new BratenDaten(); 
        b2.setName("Friedferd");
        b2.setAbholort("Arnescha");
        b2.setBeschreibung("Plataschinkenbraten aus Ö");
        b2.setHaltbarbis(LocalDate.of(2020, 04, 05));
        b2.setVgrad(50);


        BratenDaten b3 = new BratenDaten(); 
        b3.setName("Joghurta");
        b3.setAbholort("Diedlingen");
        b3.setBeschreibung("Gummibärchenbraten, frisch ");
        b3.setHaltbarbis(LocalDate.of(2020, 07, 11));
        b3.setVgrad(100);

        listBraten.add(b1);
        listBraten.add(b2);
        listBraten.add(b3);
        m.addAttribute("angebote", listBraten);
        logger.info("jaja: " + listBraten.size());
        

    }
    @GetMapping("/angebot")
    public String backV(final Model m){
        logger.info("List: " + listBraten.size());
        m.addAttribute("angebote", listBraten);
        return "angebote/liste";
    }
    @GetMapping("/angebot/neu")
    public String getForm(Model m, Locale locale){
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("angebotform",  new BratenDaten());
        return "angebote/bearbeiten";
    }
    @PostMapping("/angebot")
    public String angEinf(@ModelAttribute("angebotform") @Valid BratenDaten daten, BindingResult result, @ModelAttribute("angebote") ArrayList<BratenDaten> list, 
        Model m){

        if(result.hasErrors()){
            m.addAttribute("angebotform", daten);
            return "angebote/bearbeiten";
        }
            list.add(daten);
            return "angebote/liste";
        
        
    }
    @GetMapping("/angebot/{index}")
    public String edit(@PathVariable int index, @ModelAttribute("angebote") ArrayList<BratenDaten> list, @ModelAttribute("angebotform") BratenDaten daten, Model m){
        daten = list.get(index);
        list.remove(index);
        m.addAttribute("angebotform", daten);

        logger.info("objekt bra: {}", daten.abholort);
        return "angebote/bearbeiten"; 
    }

    @GetMapping("/angebot/{index}/delete")
    public String delete(@PathVariable int index, @ModelAttribute("angebote") ArrayList<BratenDaten> list){
        logger.info("index {}", index);
        list.remove(index);
        return "angebote/liste";
    }




 
 
    
    
}