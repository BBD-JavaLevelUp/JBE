package com.jbe.server.repository;

import com.jbe.server.entity.Inventory;
import com.jbe.server.entity.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer> {
    public Investor findBySaId(String id);
}
