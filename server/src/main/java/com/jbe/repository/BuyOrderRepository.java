package com.jbe.repository;

import com.jbe.entity.Bean;
import com.jbe.entity.BuyOrder;
import org.springframework.data.repository.CrudRepository;

public interface BuyOrderRepository extends CrudRepository<BuyOrder, Long> {

}