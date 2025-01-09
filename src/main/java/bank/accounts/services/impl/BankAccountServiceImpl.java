package bank.accounts.services.impl;

import bank.accounts.entities.BankAccount;
import bank.accounts.entities.Customer;
import bank.accounts.repository.BankAccountRepository;
import bank.accounts.repository.CustomerRepository;
import bank.accounts.services.BankAccountService;
import bank.accounts.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;

    private final TransactionService transactionService;

    @Transactional
    @Override
    public ResponseEntity<BankAccount> createNewBankAccount(long id, BankAccount bankAccount) {
        Optional<Customer> customerExist = customerRepository.findById(id);
        if (customerExist.isPresent()){
            BankAccount newBankAccount = BankAccount.builder()
                    .balance(bankAccount.getBalance())
                    .accountType(bankAccount.getAccountType())
                    .customer(customerExist.get())
                    .build();
            BankAccount savedAccount = bankAccountRepository.save(newBankAccount);
            return ResponseEntity.ok(savedAccount);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public Optional<BankAccount> findAccountById(long id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public ResponseEntity<BankAccount> deposit(long id, double amount) {
       if (amount <= 0){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
       Optional<BankAccount> bankAccountExist = bankAccountRepository.findById(id);
       if (bankAccountExist.isPresent()){
           BankAccount account = bankAccountExist.get();
           BankAccount updateBankAccount = BankAccount.builder()
                   .accountId(id)
                   .accountType(account.getAccountType())
                   .balance(account.getBalance() + amount)
                   .customer(account.getCustomer())
                   .build();
           BankAccount savedAccount = bankAccountRepository.save(updateBankAccount);
           transactionService.createDepositTransaction(account.getAccountId(), amount);
           return ResponseEntity.ok(savedAccount);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
    }

    @Override
    public ResponseEntity<BankAccount> withdraw(long id, double amount) {
        if (amount <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Optional<BankAccount> bankAccountExist = bankAccountRepository.findById(id);
        if (bankAccountExist.isPresent()){
            BankAccount account = bankAccountExist.get();

            if (account.getBalance() >= amount){
                BankAccount updateBankAccount = BankAccount.builder()
                        .accountId(id)
                        .accountType(account.getAccountType())
                        .balance(account.getBalance() - amount)
                        .customer(account.getCustomer())
                        .build();
                BankAccount saveBankAccount = bankAccountRepository.save(updateBankAccount);
                transactionService.createWithdrawTransaction(account.getAccountId(), amount);
                return ResponseEntity.ok(saveBankAccount);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
