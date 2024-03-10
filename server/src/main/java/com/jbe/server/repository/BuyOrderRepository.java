package com.jbe.server.repository;

import com.jbe.server.entity.BuyOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyOrderRepository extends CrudRepository<BuyOrder, Integer> {

}