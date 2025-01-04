package bank.accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private long transactionId;
    private String transactionType;
    private double amount;
    private String description;
    private BankAccount bankAccount;
}
