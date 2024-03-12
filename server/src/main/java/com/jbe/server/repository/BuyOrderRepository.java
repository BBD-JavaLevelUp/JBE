package com.jbe.server.repository;

import com.jbe.server.entity.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder, Integer> {

    public List<BuyOrder> findByInvestorId(int id);

    public List<BuyOrder> findByBeanId(int id);

    public List<BuyOrder> findByIsActive(boolean isActive);

    public List<BuyOrder> findByIsActiveAndInvestorId(boolean isActive, int id);

    public List<BuyOrder> findByIsActiveAndBeanId(boolean isActive, int id);

    public List<BuyOrder> findByIsActiveAndInvestorIdAndBeanId(boolean isActive, int investorId, int beanId);

}