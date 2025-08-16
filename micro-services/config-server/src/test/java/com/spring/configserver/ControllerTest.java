package com.spring.configserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"));
    }


    @Autowired
    private ObjectMapper objectMapper; // for parsing JSON

    @Test
    void testListConfigsEndpoint() throws Exception {
        MvcResult result = mockMvc.perform(get("/config"))
                .andExpect(status().isOk())
                .andReturn();

        // Parse JSON response to List
        String jsonResponse = result.getResponse().getContentAsString();
        List<String> fileNames = objectMapper.readValue(jsonResponse, List.class);

        // Assert the list has 6 elements
        assertThat(fileNames).hasSize(6);
    }


}