package bank.accounts.services.impl;

import bank.accounts.entities.BankAccount;
import bank.accounts.entities.Transaction;
import bank.accounts.repository.BankAccountRepository;
import bank.accounts.repository.TransactionRepository;

import bank.accounts.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.H2Dialect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;


    @Override
    public ResponseEntity<Transaction> createDepositTransaction(long id, double amount) {
        Optional<BankAccount> bankAccountExist = bankAccountRepository.findById(id);
        if (bankAccountExist.isPresent()){
            BankAccount account = bankAccountExist.get();
            Transaction transaction = Transaction.builder()
                    .transactionType("Deposito")
                    .amount(amount)
                    .description("Depósito de " + amount + " en la cuenta " + account.getAccountId())
                    .bankAccount(account)
                    .build();
            Transaction saveTransaction = transactionRepository.save(transaction);
            return ResponseEntity.ok(saveTransaction);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<Transaction> createWithdrawTransaction(long id, double amount) {
        Optional<BankAccount> bankAccountExist = bankAccountRepository.findById(id);
        if (bankAccountExist.isPresent()){
            BankAccount account = bankAccountExist.get();
            Transaction transaction = Transaction.builder()
                    .transactionType("Retiro")
                    .amount(amount)
                    .description("Retiro de " + amount + " en la cuenta " + account.getAccountId())
                    .bankAccount(account)
                    .build();
            Transaction saveTransaction = transactionRepository.save(transaction);
            return ResponseEntity.ok(saveTransaction);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }
}
