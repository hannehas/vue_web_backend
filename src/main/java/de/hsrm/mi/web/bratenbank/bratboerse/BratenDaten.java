package de.hsrm.mi.web.bratenbank.bratboerse;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import de.hsrm.mi.web.bratenbank.validation.GuteAdresse;

public class BratenDaten {

    @Size(min = 3, max = 80, message ="{name.fehler}")
    @NotNull
    String name;

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
    public String getName(){
        return name;
    }
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
    public void setName(String name){
        this.name = name; 
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

        return "Name: " +name+ "\n Abholort: " +abholort+ "\n Haltbar bis: "+haltbarbis+ "\n Beschreibung: "+beschreibung+ "";
    }

  

}