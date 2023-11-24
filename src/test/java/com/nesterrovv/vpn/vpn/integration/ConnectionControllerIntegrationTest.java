//package com.nesterrovv.vpn.vpn.integration;
//
//import com.nesterrovv.vpn.vpn.entity.Token;
//import com.nesterrovv.vpn.vpn.repository.TokenRepository;
//import java.util.Optional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import static org.hamcrest.Matchers.matchesPattern;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application.properties")
//public class ConnectionControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TokenRepository tokenRepository;
//
//    @BeforeEach
//    @Sql("/schema.sql")
//    public void setup() {
//        Token tokenForSave = new Token();
//        tokenForSave.setId(1L);
//        tokenForSave.setToken("test_token");
//        tokenRepository.save(tokenForSave);
//
//        Optional<Token> foundToken = tokenRepository.findById(1L);
//        System.out.println("Token added to the database: " + foundToken);
//    }
//    @Test
//    public void testFindTokenById() throws Exception {
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
//                .get("/vpn/token/get/{id}", 1L))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.token").exists());
//    }
//
//    @Test
//    public void testGenerateToken() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/vpn/token/generate"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.token",
//                matchesPattern("^ss://[A-Za-z0-9]{60}@62\\.84\\.99\\.96:1666/\\?outline=1$")));
//    }
//
//    @Test
//    public void testDeleteToken() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/vpn/token/delete/{id}", 1L))
//            .andExpect(status().isOk());
//    }
//
//}
