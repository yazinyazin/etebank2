package net.yazin.etebank.controller;

import lombok.RequiredArgsConstructor;
import net.yazin.etebank.dto.bankAccount.BankAccountDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountListDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSaveDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSearchParamsDTO;
import net.yazin.etebank.dto.transaction.TransactionAmountDTO;
import net.yazin.etebank.dto.transaction.TransactionResultDTO;
import net.yazin.etebank.entity.transactionType.DepositTransaction;
import net.yazin.etebank.entity.transactionType.WithdrawalTransaction;
import net.yazin.etebank.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("account/v1")
public class BankAccountRC {

    private final BankAccountService bankAccountService;

    @GetMapping("{number}")
    public ResponseEntity<BankAccountDTO> getAccDTO(@PathVariable("number") String accountNumber){
        return ResponseEntity.ok(bankAccountService.getAccountDTO(accountNumber));
    }

    @GetMapping("search")
    public ResponseEntity<BankAccountListDTO> search(@RequestBody BankAccountSearchParamsDTO bankAccountSearchParamsDTO){
        return ResponseEntity.ok(bankAccountService.search(bankAccountSearchParamsDTO));
    }

    @PostMapping("debit/{number}")
    public ResponseEntity<TransactionResultDTO> debit(@PathVariable("number") String accountNumber, @RequestBody TransactionAmountDTO amountDTO){
        return ResponseEntity.ok(bankAccountService.saveTransaction(new WithdrawalTransaction(amountDTO.getTransactionAmount()),accountNumber));
    }

    @PostMapping("credit/{number}")
    public ResponseEntity<TransactionResultDTO> credit(@PathVariable("number") String accountNumber, @RequestBody TransactionAmountDTO amountDTO){
        return ResponseEntity.ok(bankAccountService.saveTransaction(new DepositTransaction(amountDTO.getTransactionAmount()),accountNumber));
    }

    @PostMapping("save")
    public ResponseEntity<Void> saveAcc(@RequestParam("number") String number,@RequestParam("name")String name){
        bankAccountService.addAccount(new BankAccountSaveDTO(number,name.replace("_"," ")));
        return ResponseEntity.ok().build();
    }
}
