package de.hsrm.mi.web.bratenbank.benutzer;

public class BenutzernameSchonVergeben extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BenutzernameSchonVergeben() {
        super("Name bereits vergeben");
    }
    public BenutzernameSchonVergeben(String msg){
        super(msg);
    }
    
}