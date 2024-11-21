package net.yazin.etebank.entity.transactionType;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yazin.etebank.entity.Transaction;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WithdrawalTransaction extends Transaction {
    public double prepare(){
        return -getTransactionAmount();
    }

    public WithdrawalTransaction(double transactionAmount) {
        super(transactionAmount);
    }
}
