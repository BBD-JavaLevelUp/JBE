package com.jbe.repository;

import com.jbe.entity.Inventory;
import com.jbe.entity.Investor;
import org.springframework.data.repository.CrudRepository;

public interface InvestorRepository extends CrudRepository<Investor, Long> {

}
