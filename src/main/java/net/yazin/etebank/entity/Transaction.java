package net.yazin.etebank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int transactionId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss+SSSS") LocalDateTime transactionDate;
    private double transactionAmount;

    private UUID transactionApprovalCode;

    @ManyToOne private BankAccount transactionBankAccount;

    public double prepare(){

        return transactionAmount;
    }

    public Transaction(double transactionAmount){
        this.transactionAmount = transactionAmount;
    }

}
