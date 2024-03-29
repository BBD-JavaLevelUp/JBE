package com.jbe.server.service;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.BuyOrder;
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

    public SellOrder getSellOrdersById(int sellOrderId) {
        return sellOrderRepository.findById(sellOrderId).get();
    }

    public List<SellOrder> getSellOrdersByInvestor(int id){return sellOrderRepository.findByInvestorId(id);}

    public List<SellOrder> getSellOrdersByBean(int id){return sellOrderRepository.findByBeanId(id);}

    public List<SellOrder> getAllActiveSellOrders() {return sellOrderRepository.findByIsActive(true);}

    public List<SellOrder> getAllActiveSellOrdersByInvestor(int id) {return sellOrderRepository.findByIsActiveAndInvestorId(true, id);}

    public List<SellOrder> getAllActiveSellOrdersByBean(int id) {return sellOrderRepository.findByIsActiveAndBeanId(true, id);}

    public List<SellOrder> getAllInactiveSellOrders() {return sellOrderRepository.findByIsActive(false);}

    public List<SellOrder> getAllInactiveSellOrdersByInvestor(int id) {return sellOrderRepository.findByIsActiveAndInvestorId(false, id);}

    public List<SellOrder> getAllInactiveSellOrdersByBean(int id) {return sellOrderRepository.findByIsActiveAndBeanId(false, id);}

    public List<SellOrder> getAllSellOrdersByInvestorForBean(int investorId, int beanId){return sellOrderRepository.findByInvestorIdAndBeanId(investorId, beanId);}

    public List<SellOrder> getAllActiveSellOrdersByInvestorForBean(int investorId, int beanId){return sellOrderRepository.findByIsActiveAndInvestorIdAndBeanId(true, investorId, beanId);}

    public void saveOrUpdate(SellOrder sellOrder){
        sellOrderRepository.save(sellOrder);
    }
}
