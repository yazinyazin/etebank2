package net.yazin.etebank.service.impl;

import lombok.RequiredArgsConstructor;
import net.yazin.etebank.dao.BankAccountDAO;
import net.yazin.etebank.dto.bankAccount.BankAccountDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountListDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSaveDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSearchParamsDTO;
import net.yazin.etebank.dto.transaction.TransactionResultDTO;
import net.yazin.etebank.entity.BankAccount;
import net.yazin.etebank.entity.Transaction;
import net.yazin.etebank.exception.EtebankException;
import net.yazin.etebank.mapper.EtebankMapper;
import net.yazin.etebank.repository.BankAccountRepository;
import net.yazin.etebank.repository.TransactionRepository;
import net.yazin.etebank.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImp implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    private final TransactionRepository transactionRepository;

    private final BankAccountDAO bankAccountDAO;

    private final EtebankMapper etebankMapper;

    @Override
    public BankAccount getAccount(int id){
        return bankAccountRepository.findById(id).orElseThrow(()->new EtebankException("Bu id'ye ait bir hesap bulunamadı!", HttpStatus.BAD_REQUEST));
    }

    @Override
    public BankAccountDTO getAccountDTO(int id){
        return etebankMapper.getBankAccountDTO(bankAccountRepository.findById(id).orElseThrow(()->new EtebankException("Bu id'ye ait bir hesap bulunamadı!", HttpStatus.BAD_REQUEST)));
    }

    @Override
    public BankAccountDTO getAccountDTO(String accountNumber){
        return etebankMapper.getBankAccountDTO(bankAccountRepository.findByBankAccountNumber(accountNumber).orElseThrow(()->new EtebankException("Bu numaraya ait bir hesap bulunamadı!", HttpStatus.BAD_REQUEST)));
    }


    @Override
    public BankAccountListDTO search(BankAccountSearchParamsDTO bankAccountSearchParamsDTO){
        return new BankAccountListDTO(bankAccountDAO.search(bankAccountSearchParamsDTO),bankAccountDAO.count(bankAccountSearchParamsDTO), bankAccountSearchParamsDTO.getPageNumber(), bankAccountSearchParamsDTO.getItemCount());
    }

    @Override
    public TransactionResultDTO saveTransaction( Transaction transaction,String bankAccountNumber){
        var bankAccountId = bankAccountRepository.findBankAccountIdByBankAccountNumber(bankAccountNumber).orElseThrow(()->new EtebankException("Bu numaraya ait bir hesap bulunamadı!", HttpStatus.BAD_REQUEST));

        transaction.setTransactionBankAccount(new BankAccount(bankAccountId));

        UUID uuid = UUID.randomUUID();
        transaction.setTransactionApprovalCode(uuid);

        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);
        bankAccountRepository.updateBalance(transaction.prepare(),bankAccountNumber);

        return TransactionResultDTO.builder().transactionApprovalCode(uuid.toString()).status("OK").build();
    }

    @Override
    public BankAccount addAccount(String number){
        var account = new BankAccount(0,number);
        account.setBankAccountCreateDate(LocalDateTime.now());
        return bankAccountRepository.save(account);
    }

    @Override
    public BankAccount addAccount(BankAccountSaveDTO bankAccountSaveDTO){
        var account = etebankMapper.getBankAccount(bankAccountSaveDTO);
        account.setBankAccountCreateDate(LocalDateTime.now());
        return bankAccountRepository.save(account);
    }
}
