package net.yazin.etebank;

import net.yazin.etebank.dao.BankAccountDAO;
import net.yazin.etebank.entity.BankAccount;
import net.yazin.etebank.mapper.EtebankMapper;
import net.yazin.etebank.repository.BankAccountRepository;
import net.yazin.etebank.repository.TransactionRepository;
import net.yazin.etebank.service.impl.BankAccountServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {

    BankAccountServiceImp bankAccountService;

    @Mock BankAccountRepository bankAccountRepository;

    @Mock TransactionRepository transactionRepository;

    @Mock BankAccountDAO bankAccountDAO;

    private EtebankMapper etebankMapper;

    @BeforeEach
    void setUp()
    {
        //injectMocks will fail with MapStruct. Daha önce başıma geldi.
        this.etebankMapper= Mappers.getMapper( EtebankMapper.class );

        this.bankAccountService
                = new BankAccountServiceImp(bankAccountRepository,transactionRepository,bankAccountDAO,etebankMapper);
    }

    @Test
    void getOneAccount()
    {
        Mockito.when(bankAccountRepository.findById(1)).thenReturn(Optional.of(new BankAccount()));
        bankAccountService.getAccount(1);
        verify(bankAccountRepository).findById(1);
    }
}
