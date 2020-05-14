package de.hsrm.mi.web.hhasb001.bratboerse;

import java.sql.Date;

public class BratenDaten {
    String name; 
    String abholort; 
    Date haltbarbis; 
    String beschreibung; 

    /*Getter und Setter */
    public String getName(){
        return name;
    }
    public String getAbholort(){
        return abholort;
    }
    public Date getHaltbarbis(){
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
    public void setHaltbarbis(Date haltbarbis){
        this.haltbarbis = haltbarbis;
    }
    public void setBeschreibung(String beschreibung){
        this.beschreibung = beschreibung;
    }
    /*To String-Methode*/ 
    @Override
    public String toString(){

        return "Name: " +name+ "\n Abholort: " +abholort+ "\n Haltbar bis: "+haltbarbis+ "\n Beschreibung: "+beschreibung+ "";
    }

}