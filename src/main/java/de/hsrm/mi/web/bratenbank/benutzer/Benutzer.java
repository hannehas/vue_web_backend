package de.hsrm.mi.web.bratenbank.benutzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import de.hsrm.mi.web.bratenbank.bratrepo.Braten;

@Entity
public class Benutzer{

    @Id
    @GeneratedValue
    private long id; 

    @Version
    private long version; 

    @OneToMany(mappedBy = "anbieter")
    private Collection<Braten> angebote = new ArrayList<Braten>(); 

    @Column(name="LOGINNAME", unique=true)
    @NotEmpty(message = "Loginname fehlt")
    String loginname; 

    @Size(min = 3, message = "")
    @NotEmpty(message = "Passwort falsch")
    String passwort;

    @NotEmpty(message = "Name fehlt")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Collection<Braten> getAngebote() {
        return angebote;
    }
    
    
}