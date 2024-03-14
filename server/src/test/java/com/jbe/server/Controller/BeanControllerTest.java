package com.jbe.server.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbe.server.controller.BeanController;
import com.jbe.server.entity.Bean;
import com.jbe.server.service.BeanService;
import com.jbe.server.service.InventoryService;
import com.jbe.server.service.SellOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BeanController.class)
public class BeanControllerTest{

  @Autowired
  private MockMvc mvc;

  @MockBean
  private BeanService beanService;

  @MockBean
  private SellOrderService sellOrderService;

  @MockBean
  private InventoryService inventoryService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void getAllBeansAPI() throws Exception {
    Bean bean = new Bean("testBean", new BigDecimal("200.55"));
    List<Bean> beans = Arrays.asList(bean);

    Mockito.when(beanService.getAllBeans()).thenReturn(beans);
    mvc.perform(get("/api/beans"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(bean.getName()))
            .andExpect(jsonPath("$[0].defaultPrice").value(bean.getDefaultPrice()));
  }

  @Test
  public void getOneBeanAPI() throws Exception {
    Bean bean = new Bean("testBean", new BigDecimal("200.55"));

    Mockito.when(beanService.getBeanById(0)).thenReturn(bean);
    mvc.perform(get("/api/beans/0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(bean.getName()))
            .andExpect(jsonPath("$.defaultPrice").value(bean.getDefaultPrice()));
  }

}