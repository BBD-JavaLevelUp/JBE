package com.jbe.repository;

import com.jbe.entity.Inventory;
import org.springframework.data.repository.CrudRepository;


public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}