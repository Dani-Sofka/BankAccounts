package bank.accounts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @Column
    private String transactionType;

    @Column
    private double amount;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private BankAccount bankAccount;
}
