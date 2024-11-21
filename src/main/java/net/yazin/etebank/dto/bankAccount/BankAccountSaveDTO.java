package net.yazin.etebank.dto.bankAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yazin.etebank.dto.transaction.TransactionDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountSaveDTO {
    private String bankAccountNumber;
    private String bankAccountOwner;

}
