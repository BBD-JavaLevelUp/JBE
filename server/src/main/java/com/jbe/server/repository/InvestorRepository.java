package com.jbe.server.repository;

import com.jbe.server.entity.Investor;
import org.springframework.data.repository.CrudRepository;

public interface InvestorRepository extends CrudRepository<Investor, Long> {

}
