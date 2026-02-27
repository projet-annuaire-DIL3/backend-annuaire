package com.projet.annuaire.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.annuaire.view.SiteView;
import com.projet.annuaire.view.SalarieView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "site")
public class Site {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({SiteView.class, SalarieView.class})
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "La désignation ne peut pas être vide")
    @JsonView({SiteView.class, SalarieView.class})
    private String designation;

    @Column
    @JsonView({SiteView.class})
    private String rue;

    @Column
    @Pattern(regexp = "^\\d{5}$", message = "Le code postal doit contenir 5 chiffres")
    @JsonView({SiteView.class})
    private String cp;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "La ville ne peut pas être vide")
    @JsonView({SiteView.class, SalarieView.class})
    private String ville;

    @Column(nullable = false)
    @JsonView({SiteView.class})
    private Boolean estSiege = false;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "site")
    private List<Salarie> salaries = new ArrayList<>();
}
