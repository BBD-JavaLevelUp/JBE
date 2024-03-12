package com.jbe.server.Controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbe.server.controller.BeanController;
import com.jbe.server.entity.Bean;
import com.jbe.server.service.BeanService;
import com.jbe.server.service.SellOrderService;

@WebMvcTest(BeanController.class)
public class BeanControllerTest
{
  private static final String requestURI = "/api/beans/20";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BeanService beanService;

  @MockBean
  private SellOrderService sellOrderService;

  @Test
  public void testGetBeans() throws Exception
  {
    Bean bean = new Bean(20, "TestBean", BigDecimal.valueOf(100.00));

    Mockito.when(beanService.getBeanById(20)).thenReturn(bean);

    mockMvc.perform(get(requestURI))
      .andExpect(content().contentType("application/json"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is("TestBean")))
      .andDo(print());
  }
}
