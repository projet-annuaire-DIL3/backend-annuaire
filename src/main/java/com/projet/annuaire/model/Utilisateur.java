package com.projet.annuaire.model;

import com.projet.annuaire.security.Role;
import com.projet.annuaire.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({UtilisateurView.class})
    protected Integer id;


    @Column(unique = true, length = 100)
    @NotBlank(groups = {},
            message = "L'email ne peut pas etre vide")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}",
            groups = { })
    @JsonView({UtilisateurView.class})
    protected String email;

    @Column
    @NotBlank(groups = {})
    protected String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ADMIN')")
    @JsonView({UtilisateurView.class})
    protected Role role;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Site> sites = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Service> services = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Salarie> salaries = new ArrayList<>();

}
