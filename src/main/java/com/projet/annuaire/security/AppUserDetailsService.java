package com.projet.annuaire.security;

import com.projet.annuaire.dao.UtilisateurDao;
import com.projet.annuaire.model.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    protected final UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findByEmail(email);

        if(optionalUtilisateur.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        //retourne AppUserDetails avec role
        return new AppUserDetails(optionalUtilisateur.get());
    }
}

