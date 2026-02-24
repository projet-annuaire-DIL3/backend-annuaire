package com.projet.annuaire.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.annuaire.view.ServiceView;
import com.projet.annuaire.view.SalarieView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service")
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ServiceView.class, SalarieView.class})
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Le nom ne peut pas être vide")
    @JsonView({ServiceView.class, SalarieView.class})
    private String nom;

    @Column(length = 500)
    @JsonView({ServiceView.class})
    private String description;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Salarie> salaries = new ArrayList<>();
}
