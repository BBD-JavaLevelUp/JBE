package com.jbe.server.repository;

import com.jbe.server.entity.Investor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends CrudRepository<Investor, Integer> {

}
