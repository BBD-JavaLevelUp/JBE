package com.jbe.server.service;

import com.jbe.server.entity.Inventory;
import com.jbe.server.entity.Investor;
import com.jbe.server.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryForUserByBean(int investorId, int beanId) {
        return inventoryRepository.findByInvestorIdAndBeanId(investorId, beanId);
    }

    public List<Inventory> getInventoryForUser(int id) {
        return inventoryRepository.findByInvestorId(id);
    }

    public void saveOrUpdate(Inventory inventory){
        inventoryRepository.save(inventory);
    }
}
