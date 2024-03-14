package com.jbe.server.repository;

import com.jbe.server.entity.BuyOrder;
import com.jbe.server.entity.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BuyOrderRepositoryIntegrationTest {

    @Autowired
    private BuyOrderRepository buyOrderRepository;

    @Test
    public void testBuyOrderRepositoryFindByInvestorId() {
        BuyOrder buyOrder = new BuyOrder(2, 1, BigDecimal.valueOf(7.75), Long.valueOf(100) , Long.valueOf(1000), true);
        buyOrderRepository.save(buyOrder);

        System.out.println(buyOrder.getPrice());

        List<BuyOrder> findBuyOrder = buyOrderRepository.findByInvestorId(1);
        assertThat(findBuyOrder).isNotNull();
        BuyOrder foundBuyOrder = findBuyOrder.getFirst();
        System.out.println(foundBuyOrder.getPrice());

        assertThat(foundBuyOrder.getInvestorId()).isEqualTo(2);
        assertThat(foundBuyOrder.getBeanId()).isEqualTo(1);
//        assertThat(foundBuyOrder.getPrice().toString()).compareTo("10.75");
        assertThat(foundBuyOrder.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(7.75));
        assertThat(foundBuyOrder.getAvailableAmount()).isEqualTo((long) 100);
        assertThat(foundBuyOrder.getTotalAmount()).isEqualTo( (long) 1000);
        assertThat(foundBuyOrder.isActive()).isEqualTo(true);
    }
}