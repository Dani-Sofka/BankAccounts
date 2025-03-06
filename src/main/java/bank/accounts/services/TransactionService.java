package bank.accounts.services;

import bank.accounts.entities.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {
    ResponseEntity<Transaction> createDepositTransaction(long id, double amount);
    ResponseEntity<Transaction> createWithdrawTransaction(long id, double amount);
    List<Transaction> findAllTransactions();
}
