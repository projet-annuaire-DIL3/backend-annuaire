package com.projet.annuaire.unit_tests;

import com.projet.annuaire.model.Utilisateur;
import com.projet.annuaire.security.AppUserDetails;
import com.projet.annuaire.security.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppUserDetailsTest {

    @Test
    void createAppUserDetailsWithAdminUser_shouldHaveGrantedAuthorityEqualToROLE_ADMIN() {

        AppUserDetails appUserDetails = new AppUserDetails(
                new Utilisateur(2, "testadmin@agro.com", "root", Role.ADMIN , null, null, null ));

        Assertions.assertEquals("ROLE_ADMIN",
                appUserDetails.getAuthorities().toArray()[0].toString());
    }

}
