package com.jbe.server.repository;

import com.jbe.server.entity.Inventory;
import org.springframework.data.repository.CrudRepository;


public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}