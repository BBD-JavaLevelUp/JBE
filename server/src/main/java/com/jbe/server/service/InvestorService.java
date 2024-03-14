package com.jbe.server.service;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.Investor;
import com.jbe.server.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {
    private final InvestorRepository investorRepository;

    @Autowired
    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    public Investor getInvestorById(int investorId) {
        return investorRepository.findById(investorId).get();
    }

    public Investor getInvestorBySAId(int id) {return investorRepository.findBySaId(id);}

    public void saveOrUpdate(Investor investor){
        investorRepository.save(investor);
    }
}
