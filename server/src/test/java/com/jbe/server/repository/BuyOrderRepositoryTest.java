package com.jbe.server.repository;

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
public class BuyOrderRepositoryTest {

    @Autowired
    private BuyOrderRepository buyOrderRepository;

    @Test
    public void testBuyOrderRepositoryFindByInvestorId() {
        BuyOrder buyOrder = new BuyOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        buyOrderRepository.save(buyOrder);

        List<BuyOrder> findBuyOrder = buyOrderRepository.findByInvestorId(1);
        assertThat(findBuyOrder).isNotNull();
        BuyOrder foundBuyOrder = findBuyOrder.getLast();
        assertThat(foundBuyOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundBuyOrder.getBeanId()).isEqualTo(1);
        assertThat(foundBuyOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundBuyOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundBuyOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundBuyOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testBuyOrderRepositoryFindByBeanId() {
        BuyOrder buyOrder = new BuyOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        buyOrderRepository.save(buyOrder);

        List<BuyOrder> findBuyOrder = buyOrderRepository.findByBeanId(1);
        assertThat(findBuyOrder).isNotNull();
        BuyOrder foundBuyOrder = findBuyOrder.getLast();
        assertThat(foundBuyOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundBuyOrder.getBeanId()).isEqualTo(1);
        assertThat(foundBuyOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundBuyOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundBuyOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundBuyOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testBuyOrderRepositoryFindByIsActive() {
        BuyOrder buyOrder = new BuyOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        buyOrderRepository.save(buyOrder);

        List<BuyOrder> findBuyOrder = buyOrderRepository.findByIsActive(true);
        assertThat(findBuyOrder).isNotNull();
        BuyOrder foundBuyOrder = findBuyOrder.getLast();
        assertThat(foundBuyOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundBuyOrder.getBeanId()).isEqualTo(1);
        assertThat(foundBuyOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundBuyOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundBuyOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundBuyOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testBuyOrderRepositoryFindByIsActiveAndInvestorId() {
        BuyOrder buyOrder = new BuyOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        buyOrderRepository.save(buyOrder);

        List<BuyOrder> findBuyOrder = buyOrderRepository.findByIsActiveAndInvestorId(true,1);
        assertThat(findBuyOrder).isNotNull();
        BuyOrder foundBuyOrder = findBuyOrder.getLast();
        assertThat(foundBuyOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundBuyOrder.getBeanId()).isEqualTo(1);
        assertThat(foundBuyOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundBuyOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundBuyOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundBuyOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testBuyOrderRepositoryFindByIsActiveAndBeanId() {
        BuyOrder buyOrder = new BuyOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        buyOrderRepository.save(buyOrder);

        List<BuyOrder> findBuyOrder = buyOrderRepository.findByIsActiveAndBeanId(true,1);
        assertThat(findBuyOrder).isNotNull();
        BuyOrder foundBuyOrder = findBuyOrder.getLast();
        assertThat(foundBuyOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundBuyOrder.getBeanId()).isEqualTo(1);
        assertThat(foundBuyOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundBuyOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundBuyOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundBuyOrder.isActive()).isEqualTo(true);
    }

    @Test
    public void testBuyOrderRepositoryFindByInvestorIdAndBeanId() {
        BuyOrder buyOrder = new BuyOrder(1, 1, BigDecimal.valueOf(7.75), (long) 100, (long) 1000, true);
        buyOrderRepository.save(buyOrder);

        List<BuyOrder> findBuyOrder = buyOrderRepository.findByInvestorIdAndBeanId(1,1);
        assertThat(findBuyOrder).isNotNull();
        BuyOrder foundBuyOrder = findBuyOrder.getLast();
        assertThat(foundBuyOrder.getInvestorId()).isEqualTo(1);
        assertThat(foundBuyOrder.getBeanId()).isEqualTo(1);
        assertThat(foundBuyOrder.getPrice()).isEqualTo(BigDecimal.valueOf(7.75));
        assertThat(foundBuyOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundBuyOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundBuyOrder.isActive()).isEqualTo(true);
    }
}