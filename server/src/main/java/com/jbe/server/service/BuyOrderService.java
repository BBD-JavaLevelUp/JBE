package com.jbe.server.service;

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

    public List<BuyOrder> getBuyOrdersByInvestor(int id){return buyOrderRepository.findByBuyingInvestorId(id);}

    public List<BuyOrder> getBuyOrdersByBean(int id){return buyOrderRepository.findByBeanId(id);}

    public List<BuyOrder> getAllActiveBuyOrders() {return buyOrderRepository.findByIsActive(true);}

    public List<BuyOrder> getAllActiveBuyOrdersByInvestor(int id) {return buyOrderRepository.findByIsActiveAndBuyingInvestorId(true, id);}

    public List<BuyOrder> getAllActiveBuyOrdersByBean(int id) {return buyOrderRepository.findByIsActiveAndBeanId(true, id);}

    public List<BuyOrder> getAllInactiveBuyOrders() {return buyOrderRepository.findByIsActive(false);}

    public List<BuyOrder> getAllInactiveBuyOrdersByInvestor(int id) {return buyOrderRepository.findByIsActiveAndBuyingInvestorId(false, id);}

    public List<BuyOrder> getAllInactiveBuyOrdersByBean(int id) {return buyOrderRepository.findByIsActiveAndBeanId(false, id);}

    public void saveOrUpdate(BuyOrder buyOrder){
        buyOrderRepository.save(buyOrder);
    }
}
