package net.yazin.etebank.service.impl;

import lombok.RequiredArgsConstructor;
import net.yazin.etebank.entity.BankAccount;
import net.yazin.etebank.entity.transactionType.WithdrawalTransaction;
import net.yazin.etebank.exception.EtebankException;
import net.yazin.etebank.repository.BankAccountRepository;
import net.yazin.etebank.repository.TransactionRepository;
import net.yazin.etebank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;


}
