package net.yazin.etebank.mapper;

import net.yazin.etebank.dto.bankAccount.BankAccountDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSaveDTO;
import net.yazin.etebank.dto.transaction.TransactionDTO;
import net.yazin.etebank.entity.BankAccount;
import net.yazin.etebank.entity.Transaction;
import org.mapstruct.BeforeMapping;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
//@DecoratedWith(EtebankMapperDecorator.class)
public abstract class EtebankMapper {

    @BeforeMapping
    public void setConstraints(Transaction transaction, @MappingTarget TransactionDTO transactionDTO){
        transactionDTO.setTransactionType(transaction.getClass().getSimpleName());
    }

    public abstract BankAccountDTO getBankAccountDTO(BankAccount bankAccount);


    public abstract TransactionDTO getTransactionDTO(Transaction transaction);

    public abstract BankAccount getBankAccount(BankAccountSaveDTO bankAccountSaveDTO);

}
