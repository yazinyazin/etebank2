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
@AllArgsConstructor
public class PhoneBillPaymentTransaction extends Transaction {

    private String transactionPayee;

    private String transactionPhoneNumber;
    public double prepare(){
        return -getTransactionAmount();
    }

    public PhoneBillPaymentTransaction(String transactionPayee, String transactionPhoneNumber, double transactionAmount){
        setTransactionPayee(transactionPayee);
        setTransactionPhoneNumber(transactionPhoneNumber);
        setTransactionAmount(transactionAmount);
    }
}
