package com.jbe.service;

import com.jbe.entity.Bean;
import org.springframework.stereotype.Service;
import com.jbe.repository.BeanRepository

import java.util.List;

@Service
public class BeanService {
    private final BeanRepository beanRepository;

    public BeanService(BeanRepository beanRepository) {
        this.beanRepository = beanRepository;
    }

    public List<Bean> getAllBeans() {
        return (List<Bean>) beanRepository.findAll();
    }

}