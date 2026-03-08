package com.projet.annuaire.integration_tests;

import com.projet.annuaire.dao.ServiceDao;
import com.projet.annuaire.dao.SiteDao;
import com.projet.annuaire.model.Service;
import com.projet.annuaire.model.Site;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.json.JsonMapper;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
public class AnnuaireApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private SiteDao siteDao;

    private MockMvc mvc;

    private JsonMapper jsonMapper = JsonMapper.builder().build();

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    void callServiceList_shouldReturn200() throws Exception {
        mvc.perform(get("/api/services"))
                .andExpect(status().isOk());
    }


    @Test
    @WithUserDetails(value = "admin@agro.com")
    void callSalarieListAsAdmin_shouldReturn200() throws Exception {
        mvc.perform(get("/admin/salaries"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "admin@agro.com")
    void callProfil_shouldReturnOnlyNeededInformation() throws Exception {
        mvc.perform(get("/profil"))
                .andExpectAll(
                        jsonPath("$.password").doesNotExist(),
                        jsonPath("$.id").exists(),
                        jsonPath("$.email").exists(),
                        jsonPath("$.role").exists());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteAServiceWithoutAnyEmployeeAssociation_shouldReturn204() throws Exception {
        mvc.perform(delete("/admin/services/6"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteAServiceWithEmployeeAssociation_shouldReturn409() throws Exception {
        mvc.perform(delete("/admin/services/4"))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createServiceWithValidInformations_shouldReturn201AndProductIsCreated() throws Exception {

        Service service = new Service(null, "Nouveau Service", "Description nouveau service", null, null);
        String json = jsonMapper.writeValueAsString(service);

        mvc.perform(post("/admin/services")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())
                
                .andDo(result -> {
                   
                    String jsonNewService = result.getResponse().getContentAsString();
                    Service newService = jsonMapper.readValue(jsonNewService, Service.class );
                    Assertions.assertNotNull(newService.getId());
                    int dernierIdInsere = newService.getId();
                    Assertions.assertTrue(serviceDao.existsById(dernierIdInsere));
                });
    }


    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createSiteWithValidInformations_shouldReturn201AndSiteIsCreated() throws Exception {

        Site site = new Site(null, "Nouveau Site", "Rue Site", "57000", "Ville site", false, null, null);
        String json = jsonMapper.writeValueAsString(site);

        mvc.perform(post("/admin/sites")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())

                .andDo(result -> {

                    String jsonNewSite = result.getResponse().getContentAsString();
                    Site newSite = jsonMapper.readValue(jsonNewSite, Site.class );
                    Assertions.assertNotNull(newSite.getId());
                    int dernierIdInsere = newSite.getId();
                    Assertions.assertTrue(siteDao.existsById(dernierIdInsere));
                });
    }

}
