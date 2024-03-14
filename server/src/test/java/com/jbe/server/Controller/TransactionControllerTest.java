package com.jbe.server.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbe.server.controller.TransactionController;
import com.jbe.server.entity.*;
import com.jbe.server.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private BuyOrderService buyOrderService;

    @MockBean
    private SellOrderService sellOrderService;

    @MockBean
    private BeanService beanService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new TransactionController(transactionService, buyOrderService, beanService, sellOrderService)).build();
    }

    @Test
    public void getAllTransactionsTest() throws Exception {
        // Assume these are properly implemented with necessary setters
        Transaction transaction = new Transaction();
        BuyOrder buyOrder = new BuyOrder();
        Bean bean = new Bean();
        SellOrder sellOrder = new SellOrder();

        when(transactionService.getAllTransactions()).thenReturn(Arrays.asList(transaction));
        when(buyOrderService.getBuyOrdersById(anyInt())).thenReturn(buyOrder);
        when(beanService.getBeanById(anyInt())).thenReturn(bean);
        when(sellOrderService.getSellOrdersById(anyInt())).thenReturn(sellOrder);

        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Add more assertions as needed and repeat this pattern for the other endpoints
    }

    // Continue with similar tests for the other endpoints
}