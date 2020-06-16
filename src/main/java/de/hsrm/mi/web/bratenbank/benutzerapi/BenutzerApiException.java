package de.hsrm.mi.web.bratenbank.benutzerapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BenutzerApiException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BenutzerApiException(){
        super("Der Benutzer ist schon vergeben");
    }
    public BenutzerApiException(String msg){
        super(msg);
    }
}