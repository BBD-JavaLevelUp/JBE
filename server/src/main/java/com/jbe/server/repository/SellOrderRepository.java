package com.jbe.server.repository;

import com.jbe.server.entity.SellOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellOrderRepository extends CrudRepository<SellOrder, Integer> {

}
