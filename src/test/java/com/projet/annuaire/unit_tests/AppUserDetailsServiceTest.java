package com.projet.annuaire.unit_tests;

import com.projet.annuaire.Mock.MockUtilisateurDao;
import com.projet.annuaire.security.AppUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsServiceTest {
    @Test
    void callLoadUserByUsernameWithExistingUser_shouldReturnUserDetails() {
        AppUserDetailsService service = new AppUserDetailsService(new MockUtilisateurDao());
        Assertions.assertNotNull(service.loadUserByUsername("admin@agro.com"));
    }

    @Test
    void callLoadUserByUsernameWithNotExistingUser_shouldThrowException() {
        AppUserDetailsService service = new AppUserDetailsService(new MockUtilisateurDao());
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> service.loadUserByUsername("z@z.com"));
    }

}
