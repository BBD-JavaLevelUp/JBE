package com.jbe.server.Controller;

import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbe.server.controller.InvestorController;
import com.jbe.server.entity.Investor;
import com.jbe.server.service.InvestorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(InvestorController.class)
public class InvestorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InvestorService investorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllInvestorsTest() throws Exception {
        Investor investor = new Investor("testInvestor", "0000001234567","testInvestor@gmail.com");
        List<Investor> investors = Arrays.asList(investor);

        Mockito.when(investorService.getAllInvestors()).thenReturn(investors);
        mvc.perform(get("/api/investors"))
               .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(investor.getName()))
                .andExpect(jsonPath("$[0].saId").value(investor.getSaId()))
                .andExpect(jsonPath("$[0].email").value(investor.getEmail()));
    }

    @Test
    public void getInvestorTest() throws Exception {
        Investor investor = new Investor("testInvestor", "0000001234567","testInvestor@gmail.com"); // assume Investor has a proper constructor or setters

        Mockito.when(investorService.getInvestorById(0)).thenReturn(investor);
        mvc.perform(get("/api/investors/0"))
               .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(investor.getName()))
                .andExpect(jsonPath("$.saId").value(investor.getSaId()))
                .andExpect(jsonPath("$.email").value(investor.getEmail()));
    }

//    @Test
//    public void saveInvestorTest() throws Exception {
//        Investor investor = new Investor(); // setup your investor
//
//        doNothing().when(investorService).saveOrUpdate(any(Investor.class));
//
//        mockMvc.perform(post("/api/investors")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(new ObjectMapper().writeValueAsString(investor)))
//                    .andExpect(status().isOk());
//
//        verify(investorService).saveOrUpdate(any(Investor.class));
//    }
//
//    @Test
//    public void updateInvestorTest() throws Exception {
//        Investor investor = new Investor(); // setup your investor
//
//        doNothing().when(investorService).saveOrUpdate(any(Investor.class));
//
//        mockMvc.perform(put("/api/investors")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(new ObjectMapper().writeValueAsString(investor)))
//                    .andExpect(status().isOk());
//
//        verify(investorService).saveOrUpdate(any(Investor.class));
//    }
}
