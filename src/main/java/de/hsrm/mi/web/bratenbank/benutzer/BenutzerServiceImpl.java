package de.hsrm.mi.web.bratenbank.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class BenutzerServiceImpl implements BenutzerService{
    @Autowired BenutzerRepository benutzerrepo;

    @Override
    public boolean pruefeLogin(String loginname, String passwort) {
        // TODO Auto-generated method stub
        String pw = ermittlePasswort(loginname);
        if(passwort.equals(pw)){
            return true;
        }
        else if(!passwort.equals(pw) && loginname != ""){
           return false;
        }
        return false;
    }

    @Override
    public String ermittlePasswort(String loginname) {
        // TODO Auto-generated method stub


        Benutzer b= findeBenutzer(loginname);
        if(b != null){
            return b.getPasswort();
        }else{
            int laenge = loginname.length();
            String passwort =(new StringBuffer(loginname).append(laenge).toString());
            return passwort;
        }

       
    }

    @Override
    public Benutzer registriereBenutzer(Benutzer neubenutzer)throws BenutzernameSchonVergeben {
        // TODO Auto-generated method stub

        if(benutzerrepo.findByLoginname(neubenutzer.getLoginname()) != null){
            throw new BenutzernameSchonVergeben();
        }else{
            neubenutzer = benutzerrepo.save(neubenutzer);
            return neubenutzer;
        }
    }

    @Override
    public Benutzer findeBenutzer(String loginname) {
        // TODO Auto-generated method stub
        for(Benutzer x : benutzerrepo.findAll()){
            if(x.getLoginname().equals(loginname)){
                return x; 
            }
        }

        return null;
    }

}