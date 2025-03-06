package bank.accounts.controller;

import bank.accounts.entities.BankAccount;
import bank.accounts.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/account/create/{id}")
    public ResponseEntity<BankAccount> createAccount(@PathVariable long id, @RequestBody BankAccount bankAccount) {
        return bankAccountService.createNewBankAccount(id, bankAccount);
    }

    @GetMapping("/account/{id}")
    public Optional<BankAccount> getAccountById(@PathVariable long id){
        return bankAccountService.findAccountById(id);
    }

    @PostMapping("/account/deposit/{id}")
    public ResponseEntity<BankAccount> deposit(@PathVariable long id, @RequestBody double amount){
        return bankAccountService.deposit(id, amount);
    }

    @PostMapping("/account/withdraw/{id}")
    public ResponseEntity<BankAccount> withdraw(@PathVariable long id, @RequestBody double amount){
        return bankAccountService.withdraw(id, amount);
    }
}
