package de.hsrm.mi.web.hhasb001.bratboerse;

import java.sql.Date;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        b1.setHaltbarbis(Date.valueOf("2020-05-06"));

        BratenDaten b2 = new BratenDaten(); 
        b2.setName("Friedferd");
        b2.setAbholort("Arnescha");
        b2.setBeschreibung("Plataschinkenbraten aus Ö");
        b2.setHaltbarbis(Date.valueOf("2020-04-05"));

        BratenDaten b3 = new BratenDaten(); 
        b3.setName("Joghurta");
        b3.setAbholort("Diedlingen");
        b3.setBeschreibung("Gummibärchenbraten, frisch ");
        b3.setHaltbarbis(Date.valueOf("2020-05-07"));

        listBraten.add(b1);
        listBraten.add(b2);
        listBraten.add(b3);
        m.addAttribute("angebote", listBraten);
        logger.info("jaja: " + listBraten.size());
        

    }
    @GetMapping("/angebote")
    public String backV(final Model m){
        logger.info("List: " + listBraten.size());
        m.addAttribute("angebote", listBraten);
        return "angebote/liste";
    }
    @GetMapping("/angebote/neu")
    public String getForm(Model m){
        m.addAttribute("angebotform",  new BratenDaten());
        return "angebote/bearbeiten";
    }
    @PostMapping("/angebote")
    public String angEinf(@ModelAttribute("angebotform") BratenDaten daten, @ModelAttribute("angebote") ArrayList<BratenDaten> list){
        list.add(daten);
        return "angebote/liste";
    }
    @GetMapping("/angebote/{index}")
    public String edit(@PathVariable int index, @ModelAttribute("angebote") ArrayList<BratenDaten> list, @ModelAttribute("angebotform") BratenDaten daten, Model m){
        daten = list.get(index);
        list.remove(index);
        m.addAttribute("angebotform", daten);

        logger.info("objekt bra: {}", daten.abholort);
        return "angebote/bearbeiten"; 
    }

    @GetMapping("/angebote/{index}/delete")
    public String delete(@PathVariable int index, @ModelAttribute("angebote") ArrayList<BratenDaten> list){
        logger.info("index {}", index);
        list.remove(index);
        return "angebote/liste";
    }




 
 
    
    
}