package com.gaelle.satefynetalerts.controller;

import com.gaelle.satefynetalerts.controllers.FireStationController;
import com.gaelle.satefynetalerts.entities.FireStation;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    //private FireStation fireStation;

//    @Autowired
//    private WebApplicationContext webContext;
//
//    // initialiser un context Web (= service)
//    @Before
//    public void setupMockMvc(){
//        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
//    }

    @Test
    public void deletePerson() throws Exception{
        mockMvc.perform(delete("/api/firestation/delete/{id}",1L))
                .andExpect(status().is2xxSuccessful());

    }


}
