package com.jbe.server.service;

import com.jbe.server.entity.Bean;
import com.jbe.server.repository.BeanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeanService {
    private final BeanRepository beanRepository;
    @Autowired
    public BeanService(BeanRepository beanRepository) {
        this.beanRepository = beanRepository;
    }

    public List<Bean> getAllBeans() {
        return beanRepository.findAll();
    }

    public Bean getBeanById(int beanId) {
        return beanRepository.findById(beanId).get();
    }

    public void saveOrUpdate(Bean bean){
        beanRepository.save(bean);
    }

}