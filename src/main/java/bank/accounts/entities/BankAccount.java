package bank.accounts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "BankAccounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}
