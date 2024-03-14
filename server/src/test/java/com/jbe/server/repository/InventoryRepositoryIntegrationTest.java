package com.jbe.server.repository;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class InventoryRepositoryIntegrationTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    public void testInventoryRepositoryFindByInvestorId() {
        Inventory inventory = new Inventory(1,1, 200L);
        inventoryRepository.save(inventory);

        List<Inventory> findInventory = inventoryRepository.findByInvestorId(1);
        assertThat(findInventory).isNotNull();
        Inventory foundInventory = findInventory.getFirst();
        assertThat(foundInventory.getInventoryId()).isEqualTo(1);
        assertThat(foundInventory.getBeanId()).isEqualTo(1);
        assertThat(foundInventory.getAmount()).isEqualTo(200L);
    }
}