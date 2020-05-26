package de.hsrm.mi.web.bratenbank.benutzer;

import org.springframework.stereotype.Service;

@Service
public class BenutzerServiceImpl implements BenutzerService{

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
        int laenge = loginname.length();
        String passwort =(new StringBuffer(loginname).append(laenge).toString());
        return passwort;
    }

    @Override
    public Benutzer registriereBenutzer(Benutzer neubenutzer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Benutzer findeBenutzer(String loginname) {
        // TODO Auto-generated method stub
        return null;
    }

}