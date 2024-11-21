package net.yazin.etebank.entity.transactionType;

import jakarta.persistence.Entity;
import lombok.*;
import net.yazin.etebank.entity.Transaction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillPaymentTransaction extends Transaction {

    private String transactionPayee;

    private static final double VALUE_ADDED_TAX = 0.18;
    public double prepare(){
        return -getTransactionAmount()*(1+VALUE_ADDED_TAX); //different behavior example
    }

    public BillPaymentTransaction(double transactionAmount) {
        super(transactionAmount);
    }
}
