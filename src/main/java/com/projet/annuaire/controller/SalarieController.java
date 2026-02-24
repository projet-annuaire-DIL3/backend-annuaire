package com.projet.annuaire.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.annuaire.dao.SalarieDao;
import com.projet.annuaire.exception.GestionException;
import com.projet.annuaire.model.Salarie;
import com.projet.annuaire.security.IsAdmin;
import com.projet.annuaire.view.SalarieView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/salaries")
@RequiredArgsConstructor
public class SalarieController {

    private final SalarieDao salarieDao;

    @GetMapping
    @IsAdmin
    @JsonView(SalarieView.class)
    public List<Salarie> getAllSalaries() {
        return salarieDao.findAll();
    }

    @GetMapping("/{id}")
    @IsAdmin
    @JsonView(SalarieView.class)
    public Salarie getSalarieById(@PathVariable Integer id) {
        return salarieDao.findById(id)
                .orElseThrow(() -> GestionException.notFound("Salarié", id));
    }

    @PostMapping
    @IsAdmin
    @JsonView(SalarieView.class)
    public ResponseEntity<Salarie> createSalarie(@Valid @RequestBody Salarie salarie) {
        Salarie savedSalarie = salarieDao.save(salarie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSalarie);
    }

    @PutMapping("/{id}")
    @IsAdmin
    @JsonView(SalarieView.class)
    public Salarie updateSalarie(@PathVariable Integer id, @Valid @RequestBody Salarie salarie) {
        if (!salarieDao.existsById(id)) {
            throw GestionException.notFound("Salarié", id);
        }
        salarie.setId(id);
        return salarieDao.save(salarie);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> deleteSalarie(@PathVariable Integer id) {
        if (!salarieDao.existsById(id)) {
            throw GestionException.notFound("Salarié", id);
        }
        salarieDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
