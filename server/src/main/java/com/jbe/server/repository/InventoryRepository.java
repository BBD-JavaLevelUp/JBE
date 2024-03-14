package com.jbe.server.repository;

import com.jbe.server.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    public List<Inventory> findByInvestorId(int id);

    public Inventory findByInvestorIdAndBeanId(int investorId, int beanId);

}