package com.jbe.server.controller;

import com.jbe.server.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/investor")
public class InvestorController {
    private final InvestorService investorService;

    @Autowired
    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

//    @GetMapping
//    public List<Investor> getAllInvestors() {
//        return investorService.getAllInvestors();
//    }
}
