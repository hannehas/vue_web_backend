package de.hsrm.mi.web.bratenbank.benutzer;

import java.beans.JavaBean;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Benutzer{

    @Id
    @GeneratedValue
    private long id; 

    @Version
    private long version; 

    @Column(name="LOGINNAME", unique=true)
    @NotNull
    String loginname; 

    @Size(min = 3, max = 15)
    @NotNull
    String passwort;

    @NotNull
    String vollname;

    boolean nutzungsbedingungenok;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getVollname() {
        return vollname;
    }

    public void setVollname(String vollname) {
        this.vollname = vollname;
    }

    public boolean isNutzungsbedingungenok() {
        return nutzungsbedingungenok;
    }

    public void setNutzungsbedingungenok(boolean nutzungsbedingungenok) {
        this.nutzungsbedingungenok = nutzungsbedingungenok;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loginname == null) ? 0 : loginname.hashCode());
        result = prime * result + (nutzungsbedingungenok ? 1231 : 1237);
        result = prime * result + ((passwort == null) ? 0 : passwort.hashCode());
        result = prime * result + ((vollname == null) ? 0 : vollname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Benutzer other = (Benutzer) obj;
        if (loginname == null) {
            if (other.loginname != null)
                return false;
        } else if (!loginname.equals(other.loginname))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Benutzer [loginname=" + loginname + ", nutzungsbedingungenok=" + nutzungsbedingungenok + ", passwort="
                + passwort + ", vollname=" + vollname + "]";
    }
    
    
}