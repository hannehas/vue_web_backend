package de.hsrm.mi.web.bratenbank.benutzer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
    List<Benutzer> findByLoginname(String loginname); 
    
}