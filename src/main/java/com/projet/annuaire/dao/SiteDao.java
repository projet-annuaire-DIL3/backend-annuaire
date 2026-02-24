package com.projet.annuaire.dao;

import com.projet.annuaire.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteDao extends JpaRepository<Site, Integer> {
}
