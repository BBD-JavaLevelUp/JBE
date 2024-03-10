package com.jbe.server.repository;

import com.jbe.server.entity.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeanRepository extends CrudRepository<Bean, Integer> {
    List<Bean> findBeanByName(String beanName);
}