package com.jbe.server.service;

import com.jbe.server.entity.SellOrder;
import com.jbe.server.repository.SellOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellOrderService {
    private final SellOrderRepository sellOrderRepository;

    public SellOrderService(SellOrderRepository sellOrderRepository) {
        this.sellOrderRepository = sellOrderRepository;
    }

    public List<SellOrder> getAllSellOrders() {
        return sellOrderRepository.findAll();
    }

    public List<SellOrder> getSellOrdersByInvestor(int id){return sellOrderRepository.findBySellingInvestorId(id);}

    public List<SellOrder> getSellOrdersByBean(int id){return sellOrderRepository.findByBeanId(id);}

    public List<SellOrder> getAllActiveSellOrders() {return sellOrderRepository.findByIsActive(true);}

    public List<SellOrder> getAllActiveSellOrdersByInvestor(int id) {return sellOrderRepository.findByIsActiveAndSellingInvestorId(true, id);}

    public List<SellOrder> getAllActiveSellOrdersByBean(int id) {return sellOrderRepository.findByIsActiveAndBeanId(true, id);}

    public List<SellOrder> getAllInactiveSellOrders() {return sellOrderRepository.findByIsActive(false);}

    public List<SellOrder> getAllInactiveSellOrdersByInvestor(int id) {return sellOrderRepository.findByIsActiveAndSellingInvestorId(false, id);}

    public List<SellOrder> getAllInactiveSellOrdersByBean(int id) {return sellOrderRepository.findByIsActiveAndBeanId(false, id);}

}
