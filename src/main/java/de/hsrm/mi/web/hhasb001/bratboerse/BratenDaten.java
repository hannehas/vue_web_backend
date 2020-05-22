package de.hsrm.mi.web.hhasb001.bratboerse;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class BratenDaten {

    @Size(min = 3, max = 80, message ="Muss mindestens {min} Zeichen sein.")
    @NotNull
    String name;

    @Size(min = 1, max = 80, message = "Darf nicht leer sein.")
    @NotNull
    String abholort; 

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @NotNull(message = "Darf nicht leer, oder in der Vergangenheit sein.")
    LocalDate haltbarbis; 

    @Size(min=1, max = 80, message="Beschreibung muss mindestens {min} und maximal {max} Zeichen lang sein")
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
    public int getVgrad() {
        return vgrad;
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