package de.hsrm.mi.web.bratenbank.benutzer;

import java.util.List;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
    List<Benutzer> findByLoginname(String loginname); 
    
}