package com.jbe.server.controller;

import com.jbe.server.entity.Inventory;
import com.jbe.server.entity.SellOrder;
import com.jbe.server.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> Inventories()
    {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public List<Inventory> inventoriesForId (@PathVariable int id){
        return inventoryService.getInventoryForUser(id);
    }
}
