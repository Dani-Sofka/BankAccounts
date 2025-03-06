package bank.accounts.services;

import bank.accounts.entities.BankAccount;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface BankAccountService {
    ResponseEntity<BankAccount> createNewBankAccount(long id, BankAccount bankAccount);
    Optional<BankAccount> findAccountById(long id);
    ResponseEntity<BankAccount> deposit(long id, double amount);
    ResponseEntity<BankAccount> withdraw(long id, double amount);
}
