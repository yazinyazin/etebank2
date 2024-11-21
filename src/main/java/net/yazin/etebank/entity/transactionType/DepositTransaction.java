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
public class DepositTransaction extends Transaction {
    public double prepare(){ return getTransactionAmount(); }

    public DepositTransaction(double transactionAmount) {
        super(transactionAmount);
    }
}
