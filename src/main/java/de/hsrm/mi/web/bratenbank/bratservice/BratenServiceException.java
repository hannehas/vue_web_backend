package de.hsrm.mi.web.bratenbank.bratservice;

public class BratenServiceException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BratenServiceException() {
        super();
    }
    public BratenServiceException(String msg){
        super(msg);
    }
    
}