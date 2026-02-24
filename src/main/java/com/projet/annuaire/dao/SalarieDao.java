package com.projet.annuaire.dao;

import com.projet.annuaire.model.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalarieDao extends JpaRepository<Salarie, Integer> {
    
    // Recherche par nom ou prénom contenant les lettres saisies (case insensitive)
    List<Salarie> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
    
    // Recherche par site
    List<Salarie> findBySiteId(Integer siteId);
    
    // Recherche par service
    List<Salarie> findByServiceId(Integer serviceId);
}
