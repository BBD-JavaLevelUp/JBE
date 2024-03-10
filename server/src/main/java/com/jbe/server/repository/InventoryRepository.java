package com.jbe.server.repository;

import com.jbe.server.entity.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

}