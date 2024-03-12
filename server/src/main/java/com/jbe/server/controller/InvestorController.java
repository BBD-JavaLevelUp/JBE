package com.jbe.server.controller;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.Investor;
import com.jbe.server.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {
    private final InvestorService investorService;

    @Autowired
    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @GetMapping
    public List<Investor> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @GetMapping("/{investorId}")
    public Investor getInvestors(@PathVariable("investorId") int investorId) {
        return investorService.getInvestorById(investorId);
    }

    @PostMapping
    public int saveInvestor(@RequestBody Investor investor){
        investorService.saveOrUpdate(investor);
        return investor.getInvestorId();
    }

    @PutMapping
    public Investor updateInvestor(@RequestBody Investor investor){
        investorService.saveOrUpdate(investor);
        return investor;
    }
}
