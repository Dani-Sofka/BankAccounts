package bank.accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    private long accountId;
    private double balance;
    private String accountType;
    private Customer customer;
}
