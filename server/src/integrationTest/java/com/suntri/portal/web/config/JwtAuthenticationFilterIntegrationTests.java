package com.suntri.portal.web.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class JwtAuthenticationFilterIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenLackingUsernamePassword_thenHttp401Unauthorized() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/rest/token");

        this.mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
