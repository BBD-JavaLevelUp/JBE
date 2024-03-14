package com.jbe.server.repository;

import com.jbe.server.entity.BuyOrder;
import com.jbe.server.entity.SellOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Integer> {

    public List<SellOrder> findByInvestorId(int id);

    public List<SellOrder> findByBeanId(int id);

    public List<SellOrder> findByIsActive(boolean isActive);

    public List<SellOrder> findByIsActiveAndInvestorId(boolean isActive, int id);

    public List<SellOrder> findByIsActiveAndBeanId(boolean isActive, int id);

    public List<SellOrder> findByInvestorIdAndBeanId(int investorId, int beanId);

    public List<SellOrder> findByIsActiveAndInvestorIdAndBeanId(boolean isActive, int investorId, int beanId);

}
