package com.projet.annuaire.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.annuaire.dao.SalarieDao;
import com.projet.annuaire.dao.ServiceDao;
import com.projet.annuaire.dao.SiteDao;
import com.projet.annuaire.exception.GestionException;
import com.projet.annuaire.model.Salarie;
import com.projet.annuaire.model.Service;
import com.projet.annuaire.model.Site;
import com.projet.annuaire.view.SalarieView;
import com.projet.annuaire.view.ServiceView;
import com.projet.annuaire.view.SiteView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicController {

    private final SalarieDao salarieDao;
    private final SiteDao siteDao;
    private final ServiceDao serviceDao;


    //Afficher la fiche d'un salarié par son ID

    @GetMapping("/salaries/{id}")
    @JsonView(SalarieView.class)
    public Salarie getSalarieById(@PathVariable Integer id) {
        return salarieDao.findById(id)
                .orElseThrow(() -> GestionException.notFound("Salarié", id));
    }


    //Rechercher des salariés par nom ou prénom
   
    @GetMapping("/salaries/search")
    @JsonView(SalarieView.class)
    public List<Salarie> searchSalariesByName(@RequestParam String nom) {
        return salarieDao.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(nom, nom);
    }

  
    //Rechercher des salariés par site
  
    @GetMapping("/salaries/site/{siteId}")
    @JsonView(SalarieView.class)
    public List<Salarie> getSalariesBySite(@PathVariable Integer siteId) {
        return salarieDao.findBySiteId(siteId);
    }

    
     //Rechercher des salariés par service
    
    @GetMapping("/salaries/service/{serviceId}")
    @JsonView(SalarieView.class)
    public List<Salarie> getSalariesByService(@PathVariable Integer serviceId) {
        return salarieDao.findByServiceId(serviceId);
    }

    
     //Obtenir la liste de tous les sites (pour le filtre de recherche)
    
    @GetMapping("/sites")
    @JsonView(SiteView.class)
    public List<Site> getAllSites() {
        return siteDao.findAll();
    }

    //Obtenir la liste de tous les services (pour le filtre de recherche)
     
    @GetMapping("/services")
    @JsonView(ServiceView.class)
    public List<Service> getAllServices() {
        return serviceDao.findAll();
    }
}
