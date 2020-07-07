package de.hsrm.mi.web.bratenbank.bratservice;

import de.hsrm.mi.web.bratenbank.bratrepo.Braten;

public class BratenMessage {
    String operation;
    Braten braten;

    public BratenMessage(String operation, Braten braten){
        this.braten = braten; 
        this.operation = operation;
    }
    public String getOperation(){
        return this.operation; 
    }
    public Braten getBraten(){
        return this.braten; 
    }

    
    
}