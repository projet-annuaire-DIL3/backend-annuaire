package com.projet.annuaire.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.annuaire.view.SalarieView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salarie")
public class Salarie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({SalarieView.class})
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Le nom ne peut pas être vide")
    @JsonView({SalarieView.class})
    private String nom;

    @Column(nullable = false)
    @NotBlank(message = "Le prénom ne peut pas être vide")
    @JsonView({SalarieView.class})
    private String prenom;

    @Column
    @JsonView({SalarieView.class})
    private String telephoneFixe;

    @Column
    @JsonView({SalarieView.class})
    private String telephonePortable;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "L'email doit être valide")
    @JsonView({SalarieView.class})
    private String email;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    @JsonView({SalarieView.class})
    private Service service;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    @JsonView({SalarieView.class})
    private Site site;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
