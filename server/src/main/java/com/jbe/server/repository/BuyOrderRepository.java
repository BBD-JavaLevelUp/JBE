package com.jbe.server.repository;

import com.jbe.server.entity.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder, Integer> {

}