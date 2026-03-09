
package com.projet.annuaire.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.projet.annuaire.Mock.MockSalarieDao;
import com.projet.annuaire.controller.PublicController;


public class PublicControllerTest {
    @Test
    void findSalarieByName_shouldReturnSalarieDetails() {
       
        var controller = new PublicController(
            new MockSalarieDao(),
            null,
            null
        );

        var result = controller.searchSalariesByName("TestSalarieNom");

        // Assert
        Assertions.assertEquals(1, result.size());
        var salarie = result.get(0);
        Assertions.assertEquals("TestSalarieNom", salarie.getNom());
        Assertions.assertEquals("TestSalariePrenom", salarie.getPrenom());
    }
}
