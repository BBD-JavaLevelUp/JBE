package com.jbe.server.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
void testGetAllBeans() throws Exception 
  {
    List<Bean> beans = List.of(new Bean(20, "TestBean1", BigDecimal.valueOf(100.00)), new Bean(21, "TestBean2", BigDecimal.valueOf(150.00))); // Create some mock beans
    when(beanService.getAllBeans()).thenReturn(beans);
    // Add logic to mock sellOrderService as needed

    mockMvc.perform(get("/api/beans"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(beans.size()))); // Validate the response

    verify(beanService).getAllBeans(); // Ensure service method was called
  }


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

  @Test
void testSaveBean() throws Exception {
    Bean bean = new Bean(23, "TestBean3", BigDecimal.valueOf(180.00));
    

    mockMvc.perform(post("/api/beans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(bean)))
           .andExpect(status().isOk())
           .andExpect(content().string(String.valueOf(bean.getBeanId())));

    verify(beanService).saveOrUpdate(any(Bean.class));
}
}
