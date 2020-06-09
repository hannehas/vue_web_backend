package de.hsrm.mi.web.bratenbank.bratrepo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.validation.GuteAdresse;

@Entity
public class Braten {

    @Id
    @GeneratedValue
    private int id; 

    @Version
    private int version; 
    
    @ManyToOne
    Benutzer anbieter; 
    
    @GuteAdresse(message = "{abholort.fehler}")
    String abholort; 

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    @NotNull(message = "{haltbarbis.fehler}")
    LocalDate haltbarbis; 

    @Size(min=1, max = 80, message="{beschreibung.fehler}")
    @NotNull
    String beschreibung; 

    int vgrad;

    /*Getter und Setter */
    public String getAbholort(){
        return abholort;
    }
    public LocalDate getHaltbarbis(){
        return haltbarbis;
    }
    public String getBeschreibung(){
        return beschreibung;
    } 
    public int getVgrad() {
        return vgrad;
    }
    public void setAbholort(String abholort){
        this.abholort = abholort;
    }
    public void setHaltbarbis(LocalDate haltbarbis){
        this.haltbarbis = haltbarbis;
    }
    public void setBeschreibung(String beschreibung){
        this.beschreibung = beschreibung;
    } 
    public void setVgrad(int vgrad) {
        this.vgrad = vgrad;
    }
    /*To String-Methode*/ 
    @Override
    public String toString(){

        return "Abholort: " +abholort+ "\n Haltbar bis: "+haltbarbis+ "\n Beschreibung: "+beschreibung+ "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Benutzer getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(Benutzer anbieter) {
        this.anbieter = anbieter;
    }

  

}