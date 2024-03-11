package com.jbe.server.controller;

import com.jbe.server.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

//    @GetMapping
//    public Optional<Inventory> getInventoryForOneUser(int userId) {
//        return inventoryService.getInventoryForOneUser(userId);
//    }
}
