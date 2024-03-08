package com.jbe.server.repository;

import com.jbe.server.entity.Bean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeanRepository extends CrudRepository<Bean, Long> {
    List<Bean> findBeanByName(String beanName);
}