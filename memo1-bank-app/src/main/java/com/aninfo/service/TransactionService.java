package com.aninfo.service;

import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private Double calculateTotalSum(Double sum) {
        if (sum >= 2000.0) {
            Double promoSum = sum*0.1;
            sum += Math.min(promoSum, 500.0);
        }
        return sum;
    }

    public Transaction createDeposit(Long cbu, Double sum) {
        sum = this.calculateTotalSum(sum);
        Transaction transaction = new Transaction(cbu, sum, "deposit");

        return transactionRepository.save(transaction);
    }

    public Transaction createWithdraw(Long cbu, Double sum) {
        Transaction transaction = new Transaction(cbu, sum, "withdraw");

        return transactionRepository.save(transaction);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactionsFrom(Long cbu) {
        return transactionRepository.findByCbu(cbu);
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id); }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
