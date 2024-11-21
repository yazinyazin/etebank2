package net.yazin.etebank.repository;

import net.yazin.etebank.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> {
    Optional<BankAccount> findByBankAccountNumber(String bankAccountNumber);

    @Query("SELECT b.bankAccountId FROM BankAccount b WHERE b.bankAccountNumber = :var")
    Optional<Integer> findBankAccountIdByBankAccountNumber(@Param("var") String bankAccountNumber);


    @Modifying
    @Query("UPDATE BankAccount b SET b.bankAccountBalance = b.bankAccountBalance + :var1 WHERE b.bankAccountNumber = :var2")
    void updateBalance(@Param("var1") double transactionAmount,@Param("var2") String bankAccountNumber);

}
