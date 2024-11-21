package net.yazin.etebank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BankAccount {
    public BankAccount(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int bankAccountId;
    @Column(unique = true) String bankAccountNumber;
    private String bankAccountOwner;
    private double bankAccountBalance;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss+SSSS") LocalDateTime bankAccountCreateDate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "transactionBankAccount",fetch = FetchType.EAGER)
    List<Transaction> bankAccountTransactions;

    public void post(Transaction transaction){
        if(this.bankAccountTransactions==null){
            this.bankAccountTransactions = new ArrayList<>();
        }

        transaction.setTransactionDate(LocalDateTime.now());

        this.bankAccountTransactions.add(transaction);
        this.bankAccountBalance+=transaction.prepare();
    }

    public void credit(Double amount){
        this.bankAccountBalance+=amount;
    }

    public void debit(Double amount){
        this.bankAccountBalance-=amount;
    }

    public BankAccount(String bankAccountOwner, String bankAccountNumber){
        this.bankAccountOwner=bankAccountOwner;
        this.bankAccountNumber=bankAccountNumber;
    }
    public BankAccount(double bankAccountBalance, String bankAccountNumber){
        this.bankAccountBalance=bankAccountBalance;
        this.bankAccountNumber=bankAccountNumber;
    }

}
