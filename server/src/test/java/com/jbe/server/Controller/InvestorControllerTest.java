package com.jbe.server.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbe.server.controller.InvestorController;
import com.jbe.server.entity.Investor;
import com.jbe.server.service.InvestorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

public class InvestorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InvestorService investorService;

    @InjectMocks
    private InvestorController investorController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(investorController).build();
    }

    @Test
    public void getAllInvestorsTest() throws Exception {
        List<Investor> investors = Arrays.asList(new Investor(), new Investor());
        given(investorService.getAllInvestors()).willReturn(investors);

        mockMvc.perform(get("/api/investors"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(investorService).getAllInvestors();
    }

    @Test
    public void getInvestorTest() throws Exception {
        Investor investor = new Investor(); // assume Investor has a proper constructor or setters
        given(investorService.getInvestorById(1)).willReturn(investor);

        mockMvc.perform(get("/api/investors/{investorId}", 1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(investorService).getInvestorById(1);
    }

    @Test
    public void saveInvestorTest() throws Exception {
        Investor investor = new Investor(); // setup your investor

        doNothing().when(investorService).saveOrUpdate(any(Investor.class));

        mockMvc.perform(post("/api/investors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(investor)))
                    .andExpect(status().isOk());

        verify(investorService).saveOrUpdate(any(Investor.class));
    }

    @Test
    public void updateInvestorTest() throws Exception {
        Investor investor = new Investor(); // setup your investor

        doNothing().when(investorService).saveOrUpdate(any(Investor.class));

        mockMvc.perform(put("/api/investors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(investor)))
                    .andExpect(status().isOk());

        verify(investorService).saveOrUpdate(any(Investor.class));
    }
}
