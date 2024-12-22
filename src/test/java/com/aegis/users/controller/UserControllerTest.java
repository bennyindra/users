package com.aegis.users.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("unused")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testEmptyRecord() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"page\":1,\"total_page\":0,\"content\":[]}")));
    }

    @Test
    @Order(2)
    void testCreateAndGetRecord() throws Exception {
        this.mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"bennyindra.official@gmail.com\",\"name\": \"John Doe\",\"password\": \"xxxyyy\"}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertEquals(1, result.getResponse().getContentAsString().split("\"total_page\":").length - 1);
                    assertEquals(1, result.getResponse().getContentAsString().split("\"page\":").length - 1);
                    assertEquals(1, result.getResponse().getContentAsString().split("\"content\":").length - 1);
                    assertTrue(result.getResponse().getContentAsString().contains("\"name\":\"John Doe\""));
                    assertTrue(result.getResponse().getContentAsString().contains("\"email\":\"bennyindra.official@gmail.com\""));
                });
    }

}