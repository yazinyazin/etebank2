package net.yazin.etebank.service;

import net.yazin.etebank.dto.bankAccount.BankAccountDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountListDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSaveDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSearchParamsDTO;
import net.yazin.etebank.dto.transaction.TransactionResultDTO;
import net.yazin.etebank.entity.BankAccount;
import net.yazin.etebank.entity.Transaction;
import org.springframework.transaction.annotation.Transactional;

public interface BankAccountService {
    BankAccount getAccount(int id);

    BankAccountDTO getAccountDTO(int id);

    BankAccountDTO getAccountDTO(String accountNumber);

    BankAccountListDTO search(BankAccountSearchParamsDTO bankAccountSearchParamsDTO);

    @Transactional
    TransactionResultDTO saveTransaction(Transaction transaction, String bankAccountNumber);

    BankAccount addAccount(String number);

    BankAccount addAccount(BankAccountSaveDTO bankAccountSaveDTO);
}
