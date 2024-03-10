package com.jbe.server.repository;

import com.jbe.server.entity.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeanRepository extends JpaRepository<Bean, Integer> {

//    List<Bean> findBeanByName(String beanName);
}