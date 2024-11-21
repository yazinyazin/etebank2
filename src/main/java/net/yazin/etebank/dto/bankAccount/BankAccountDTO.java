package net.yazin.etebank.dto.bankAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.yazin.etebank.dto.transaction.TransactionDTO;
import net.yazin.etebank.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BankAccountDTO {
    private String bankAccountNumber;
    private String bankAccountOwner;
    private double bankAccountBalance;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss+SSSS") LocalDateTime bankAccountCreateDate;


    List<TransactionDTO> bankAccountTransactions;

}
