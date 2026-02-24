package com.projet.annuaire.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.annuaire.dao.ServiceDao;
import com.projet.annuaire.exception.GestionException;
import com.projet.annuaire.model.Service;
import com.projet.annuaire.security.IsAdmin;
import com.projet.annuaire.view.ServiceView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceDao serviceDao;

    @GetMapping
    @IsAdmin
    @JsonView(ServiceView.class)
    public List<Service> getAllServices() {
        return serviceDao.findAll();
    }

    @GetMapping("/{id}")
    @IsAdmin
    @JsonView(ServiceView.class)
    public Service getServiceById(@PathVariable Integer id) {
        return serviceDao.findById(id)
                .orElseThrow(() -> GestionException.notFound("Service", id));
    }

    @PostMapping
    @IsAdmin
    @JsonView(ServiceView.class)
    public ResponseEntity<Service> createService(@Valid @RequestBody Service service) {
        Service savedService = serviceDao.save(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
    }

    @PutMapping("/{id}")
    @IsAdmin
    @JsonView(ServiceView.class)
    public Service updateService(@PathVariable Integer id, @Valid @RequestBody Service service) {
        if (!serviceDao.existsById(id)) {
            throw GestionException.notFound("Service", id);
        }
        service.setId(id);
        return serviceDao.save(service);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        if (!serviceDao.existsById(id)) {
            throw GestionException.notFound("Service", id);
        }
        serviceDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
