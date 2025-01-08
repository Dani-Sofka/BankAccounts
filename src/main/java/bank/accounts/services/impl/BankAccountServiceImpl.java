package bank.accounts.services.impl;

import bank.accounts.entities.BankAccount;
import bank.accounts.entities.Customer;
import bank.accounts.repository.BankAccountRepository;
import bank.accounts.repository.CustomerRepository;
import bank.accounts.services.BankAccountService;
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
}
