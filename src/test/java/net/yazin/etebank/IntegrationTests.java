package net.yazin.etebank;

import net.yazin.etebank.dto.bankAccount.BankAccountSaveDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSearchParamsDTO;
import net.yazin.etebank.entity.BankAccount;
import net.yazin.etebank.entity.transactionType.DepositTransaction;
import net.yazin.etebank.entity.transactionType.PhoneBillPaymentTransaction;
import net.yazin.etebank.entity.transactionType.WithdrawalTransaction;
import net.yazin.etebank.service.BankAccountService;
import net.yazin.etebank.service.TransactionService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test") //db is create-drop
public class IntegrationTests {

    @Autowired private BankAccountService bankAccountService;

    @Autowired private TransactionService transactionService;

    @Test
    @Order(1)
    void gitlab_test(){
        BankAccount account = new BankAccount("Jim", "12345");
        account.post(new DepositTransaction(1000));
        account.post(new WithdrawalTransaction(200));
        account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));

        assertEquals(account.getBankAccountBalance(), 703.50, 0.0001);

    }

    @Test
    @Order(2)
    void saveAccount_then_getAccount(){
        BankAccountSaveDTO account = new BankAccountSaveDTO("12345","Jim");
        bankAccountService.addAccount(account);

        assertEquals("Jim",bankAccountService.getAccount(1).getBankAccountOwner());


    }

    @Test
    @Order(3)
    void saveTransactions_then_getBalance(){
        bankAccountService.saveTransaction(new DepositTransaction(100),"12345");
        bankAccountService.saveTransaction(new DepositTransaction(200),"12345");
        bankAccountService.saveTransaction(new WithdrawalTransaction(75),"12345");

        var account =bankAccountService.getAccount(1);
        assertEquals(225,account.getBankAccountBalance());
        assertEquals(3,account.getBankAccountTransactions().size());


    }

    @Test
    @Order(4)
    void search(){
        BankAccountSearchParamsDTO bankAccountSearchParamsDTO = BankAccountSearchParamsDTO.builder()
                .bankAccountNumberContains("12")
                .itemCount(10)
                .pageNumber(0)
                .build();

        var searchResult1 = bankAccountService.search(bankAccountSearchParamsDTO);
        assertEquals(searchResult1.getTotalDataCount(),1);
        assertEquals(searchResult1.getList().get(0).getBankAccountOwner(),"Jim");


        BankAccountSearchParamsDTO bankAccountSearchParamsDTO2 = BankAccountSearchParamsDTO.builder()
                .bankAccountNumberContains("98")
                .itemCount(10)
                .pageNumber(0)
                .build();

        var searchResult2 = bankAccountService.search(bankAccountSearchParamsDTO2);
        assertEquals(searchResult2.getTotalDataCount(),0);
        assertEquals(searchResult2.getList().size(),0);


        BankAccountSearchParamsDTO bankAccountSearchParamsDTO3 = BankAccountSearchParamsDTO.builder()
                .bankAccountBalanceGreaterThan(200D)
                .itemCount(10)
                .pageNumber(0)
                .build();

        var searchResult3 = bankAccountService.search(bankAccountSearchParamsDTO3);
        assertEquals(searchResult3.getTotalDataCount(),1);
        assertEquals(searchResult3.getList().get(0).getBankAccountNumber(),"12345");

        BankAccountSearchParamsDTO bankAccountSearchParamsDTO4 = BankAccountSearchParamsDTO.builder()
                .itemCount(10)
                .pageNumber(0)
                .build();

        BankAccountSaveDTO account = new BankAccountSaveDTO("42","Ford Prefect");
        bankAccountService.addAccount(account);


        var searchResult4 = bankAccountService.search(bankAccountSearchParamsDTO4);
        assertEquals(searchResult4.getTotalDataCount(),2);
        assertEquals(searchResult4.getList().get(0).getBankAccountOwner(),"Jim");
        assertEquals(searchResult4.getList().get(1).getBankAccountOwner(),"Ford Prefect");



    }
}
