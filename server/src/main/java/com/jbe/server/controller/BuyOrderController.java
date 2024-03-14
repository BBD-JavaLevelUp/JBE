package com.jbe.server.controller;

import com.jbe.server.entity.*;
import com.jbe.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.min;

@RestController
@RequestMapping("/api/buy-orders")
public class BuyOrderController {
    private final BuyOrderService buyOrderService;
    private final SellOrderService sellOrderService;
    private final TransactionService transactionService;
    private final BeanService beanService;
    private final InventoryService inventoryService;

    @Autowired
    public BuyOrderController(BuyOrderService buyOrderService, SellOrderService sellOrderService, TransactionService transactionService, BeanService beanService, InventoryService inventoryService) {
        this.buyOrderService = buyOrderService;
        this.sellOrderService = sellOrderService;
        this.transactionService = transactionService;
        this.beanService = beanService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<BuyOrder> getAllBuyOrders() {
        return buyOrderService.getAllBuyOrders().stream().map(b -> {
            BuyOrder n = new BuyOrder(b);
            Bean bean = beanService.getBeanById(b.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/{buyOrderId}")
    public BuyOrder buyOrdersById(@PathVariable("buyOrderId") int buyOrderId)
    {
        BuyOrder n = new BuyOrder(buyOrderService.getBuyOrdersById(buyOrderId));
        Bean bean = beanService.getBeanById(n.getBeanId());
        n.setBeanName(bean.getName());
        return n;
    }

    @GetMapping("/investor/{id}")
    public List<BuyOrder> buyOrdersByInvestor(@PathVariable int id)
    {
        return buyOrderService.getBuyOrdersByInvestor(id).stream().map(b -> {
        BuyOrder n = new BuyOrder(b);
        Bean bean = beanService.getBeanById(b.getBeanId());
        n.setBeanName(bean.getName());
        return n;
    }).toList();
    }

    @GetMapping("/beans/{id}")
    public List<BuyOrder> buyOrdersByBean(@PathVariable int id)
    {
        return buyOrderService.getBuyOrdersByBean(id).stream().map(b -> {
            BuyOrder n = new BuyOrder(b);
            Bean bean = beanService.getBeanById(b.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/active")
    public List<BuyOrder> activeBuyOrders()
    {
        return buyOrderService.getAllActiveBuyOrders().stream().map(b -> {
            BuyOrder n = new BuyOrder(b);
            Bean bean = beanService.getBeanById(b.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/active/investor/{id}")
    public List<BuyOrder> activeBuyOrdersByInvestor(@PathVariable int id)
    {
        return buyOrderService.getAllActiveBuyOrdersByInvestor(id).stream().map(b -> {
            BuyOrder n = new BuyOrder(b);
            Bean bean = beanService.getBeanById(b.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/active/beans/{id}")
    public List<BuyOrder> activeBuyOrdersByBean(@PathVariable int id)
    {
        return buyOrderService.getAllActiveBuyOrdersByBean(id).stream().map(b -> {
            BuyOrder n = new BuyOrder(b);
            Bean bean = beanService.getBeanById(b.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/inactive")
    public List<BuyOrder> inactiveBuyOrders()
    {
        return buyOrderService.getAllInactiveBuyOrders().stream().map(b -> {
            BuyOrder n = new BuyOrder(b);
            Bean bean = beanService.getBeanById(b.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/inactive/investor/{id}")
    public List<BuyOrder> inactiveBuyOrdersByInvestor(@PathVariable int id)
    {
        return buyOrderService.getAllInactiveBuyOrdersByInvestor(id).stream().map(b -> {
            BuyOrder n = new BuyOrder(b);
            Bean bean = beanService.getBeanById(b.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/inactive/beans/{id}")
    public List<BuyOrder> inactiveBuyOrdersByBean(@PathVariable int id)
    {
        return buyOrderService.getAllInactiveBuyOrdersByBean(id).stream().map(b -> {
            BuyOrder n = new BuyOrder(b);
            Bean bean = beanService.getBeanById(b.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @PostMapping
    public int saveBean(@RequestBody BuyOrder buyOrder){
        buyOrderService.saveOrUpdate(buyOrder);
        matchBuyOrder(buyOrder);
        return buyOrder.getBeanId();
    }

    @PutMapping
    public int updateBean(@RequestBody BuyOrder buyOrder){
        buyOrderService.saveOrUpdate(buyOrder);
        matchBuyOrder(buyOrder);
        return buyOrder.getBeanId();
    }

    public void matchBuyOrder(BuyOrder buyOrder){
        while (buyOrder.getAvailableAmount()>0){
            buyOrder.setActive(true);
            List<SellOrder> sellOrders = new java.util.ArrayList<>(sellOrderService.getAllActiveSellOrdersByBean(buyOrder.getBeanId()).stream().filter(s -> s.getPrice().compareTo(buyOrder.getPrice()) <= 0).toList());
            sellOrders.sort(Comparator.comparing(SellOrder::getOrderDate));
            if (!sellOrders.isEmpty()){
                SellOrder sellOrder = sellOrders.getFirst();
                Inventory sellerInventory = inventoryService.getInventoryForUserByBean(sellOrder.getInvestorId(), sellOrder.getBeanId());
                Inventory buyerInventory = inventoryService.getInventoryForUserByBean(buyOrder.getInvestorId(), buyOrder.getBeanId());
                if (buyerInventory==null){
                    buyerInventory = new Inventory(buyOrder.getInvestorId(), buyOrder.getBeanId(), 0L);
                }
                long total = min(List.of(sellOrder.getAvailableAmount(), buyOrder.getAvailableAmount()));
                Transaction transaction = new Transaction(buyOrder.getBuyOrderId(), sellOrder.getSellOrderId(), total);
                sellOrder.setAvailableAmount(sellOrder.getAvailableAmount()-total);
                buyOrder.setAvailableAmount(buyOrder.getAvailableAmount()-total);
                sellerInventory.setAmount(sellerInventory.getAmount()-total);
                buyerInventory.setAmount(buyerInventory.getAmount()+total);
                if (buyOrder.getAvailableAmount()==0){

                    List<Transaction> transactions = transactionService.getTransactionByBuyOrderId(buyOrder.getBuyOrderId());
                    transactions.add(transaction);
                    buyOrder.setPrice(transactions.stream().map(t -> t.getPrice().multiply(BigDecimal.valueOf(t.getAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(buyOrder.getTotalAmount())));
                    buyOrder.setActive(false);
                }
                if (sellOrder.getAvailableAmount()==0){
                    sellOrder.setActive(false);
                }
                transactionService.saveOrUpdate(transaction);
                buyOrderService.saveOrUpdate(buyOrder);
                sellOrderService.saveOrUpdate(sellOrder);
                inventoryService.saveOrUpdate(sellerInventory);
                inventoryService.saveOrUpdate(buyerInventory);
            } else {
                return;
            }
        }
    }
}
