package com.jbe.server.repository;

import com.jbe.server.entity.BuyOrder;
import com.jbe.server.entity.SellOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import com.jbe.server.entity.BuyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class SellOrderRepositoryTest {

    @Autowired
    private SellOrderRepository sellOrderRepository;

    @Test
    public void testSellOrderRepositoryFindByInvestorId() {
        SellOrder sellOrder = new SellOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        sellOrderRepository.save(sellOrder);

        List<SellOrder> findSellOrder = sellOrderRepository.findByInvestorId(1);
        assertThat(findSellOrder).isNotNull();
        SellOrder foundSellOrder = findSellOrder.getLast();
        assertThat(foundSellOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundSellOrder.getBeanId()).isEqualTo(1);
        assertThat(foundSellOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundSellOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundSellOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundSellOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testSellOrderRepositoryFindByBeanId() {
        SellOrder sellOrder = new SellOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        sellOrderRepository.save(sellOrder);

        List<SellOrder> findSellOrder = sellOrderRepository.findByBeanId(1);
        assertThat(findSellOrder).isNotNull();
        SellOrder foundSellOrder = findSellOrder.getLast();
        assertThat(foundSellOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundSellOrder.getBeanId()).isEqualTo(1);
        assertThat(foundSellOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundSellOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundSellOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundSellOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testSellOrderRepositoryFindByIsActive() {
        SellOrder sellOrder = new SellOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        sellOrderRepository.save(sellOrder);

        List<SellOrder> findSellOrder = sellOrderRepository.findByIsActive(true);
        assertThat(findSellOrder).isNotNull();
        SellOrder foundSellOrder = findSellOrder.getLast();
        assertThat(foundSellOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundSellOrder.getBeanId()).isEqualTo(1);
        assertThat(foundSellOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundSellOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundSellOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundSellOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testSellOrderRepositoryFindByIsActiveAndInvestorId() {
        SellOrder sellOrder = new SellOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        sellOrderRepository.save(sellOrder);

        List<SellOrder> findSellOrder = sellOrderRepository.findByIsActiveAndInvestorId(true,1);
        assertThat(findSellOrder).isNotNull();
        SellOrder foundSellOrder = findSellOrder.getLast();
        assertThat(foundSellOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundSellOrder.getBeanId()).isEqualTo(1);
        assertThat(foundSellOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundSellOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundSellOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundSellOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testSellOrderRepositoryFindByIsActiveAndBeanId() {
        SellOrder sellOrder = new SellOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        sellOrderRepository.save(sellOrder);

        List<SellOrder> findSellOrder = sellOrderRepository.findByIsActiveAndBeanId(true,1);
        assertThat(findSellOrder).isNotNull();
        SellOrder foundSellOrder = findSellOrder.getLast();
        assertThat(foundSellOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundSellOrder.getBeanId()).isEqualTo(1);
        assertThat(foundSellOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundSellOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundSellOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundSellOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testSellOrderRepositoryFindByInvestorIdAndBeanId() {
        SellOrder sellOrder = new SellOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        sellOrderRepository.save(sellOrder);

        List<SellOrder> findSellOrder = sellOrderRepository.findByInvestorIdAndBeanId(1,1);
        assertThat(findSellOrder).isNotNull();
        SellOrder foundSellOrder = findSellOrder.getLast();
        assertThat(foundSellOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundSellOrder.getBeanId()).isEqualTo(1);
        assertThat(foundSellOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundSellOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundSellOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundSellOrder.isActive()).isEqualTo(true);
    }
}