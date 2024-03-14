package com.jbe.server.repository;

import com.jbe.server.entity.BuyOrder;
import com.jbe.server.entity.SellOrder;
import com.jbe.server.entity.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@RunWith(SpringRunner.class)
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testTransactionRepositoryFindByBuyOrderId() {
        Transaction transaction = new Transaction(1, 1, 500);
        transactionRepository.save(transaction);

        List<Transaction> findTransaction = transactionRepository.findByBuyOrderId(1);
        assertThat(findTransaction).isNotNull();
        Transaction foundTransaction = findTransaction.getLast();
        assertThat(foundTransaction.getBuyOrderId()).isEqualTo(1);
        assertThat(foundTransaction.getSellOrderId()).isEqualTo(1);
        assertThat(foundTransaction.getAmount()).isEqualTo(500);
    }

    @Test
    public void testTransactionRepositoryFindBySellOrderId() {
        Transaction transaction = new Transaction(1, 1, 500);
        transactionRepository.save(transaction);

        List<Transaction> findTransaction = transactionRepository.findBySellOrderId(1);
        assertThat(findTransaction).isNotNull();
        Transaction foundTransaction = findTransaction.getLast();
        assertThat(foundTransaction.getBuyOrderId()).isEqualTo(1);
        assertThat(foundTransaction.getSellOrderId()).isEqualTo(1);
        assertThat(foundTransaction.getAmount()).isEqualTo(500);
    }

}