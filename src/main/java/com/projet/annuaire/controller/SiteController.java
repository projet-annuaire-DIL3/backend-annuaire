package com.projet.annuaire.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.annuaire.dao.SiteDao;
import com.projet.annuaire.exception.GestionException;
import com.projet.annuaire.model.Site;
import com.projet.annuaire.security.IsAdmin;
import com.projet.annuaire.view.SiteView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sites")
@RequiredArgsConstructor
public class SiteController {

    private final SiteDao siteDao;

    @GetMapping
    @IsAdmin
    @JsonView(SiteView.class)
    public List<Site> getAllSites() {
        return siteDao.findAll();
    }

    @GetMapping("/{id}")
    @IsAdmin
    @JsonView(SiteView.class)
    public Site getSiteById(@PathVariable Integer id) {
        return siteDao.findById(id)
                .orElseThrow(() -> GestionException.notFound("Site", id));
    }

    @PostMapping
    @IsAdmin
    @JsonView(SiteView.class)
    public ResponseEntity<Site> createSite(@Valid @RequestBody Site site) {
        Site savedSite = siteDao.save(site);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSite);
    }

    @PutMapping("/{id}")
    @IsAdmin
    @JsonView(SiteView.class)
    public Site updateSite(@PathVariable Integer id, @Valid @RequestBody Site site) {
        if (!siteDao.existsById(id)) {
            throw GestionException.notFound("Site", id);
        }
        site.setId(id);
        return siteDao.save(site);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> deleteSite(@PathVariable Integer id) {
        if (!siteDao.existsById(id)) {
            throw GestionException.notFound("Site", id);
        }
        siteDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
