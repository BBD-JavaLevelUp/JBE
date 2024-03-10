package com.jbe.server.service;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.BuyOrder;
import com.jbe.server.repository.BuyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    @Autowired
    public BuyOrderService(BuyOrderRepository buyOrderRepository) {
        this.buyOrderRepository = buyOrderRepository;
    }

    public List<BuyOrder> getAllBuyOrders() {
        return buyOrderRepository.findAll();
    }

    public void saveOrUpdate(BuyOrder buyOrder){
        buyOrderRepository.save(buyOrder);
    }
}
