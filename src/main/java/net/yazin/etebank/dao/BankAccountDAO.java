package net.yazin.etebank.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import net.yazin.etebank.dto.bankAccount.BankAccountListItemDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSearchParamsDTO;
import net.yazin.etebank.entity.BankAccount;

import java.util.List;

public interface BankAccountDAO {

    List<Predicate> predicateMaker(CriteriaBuilder cb, Root<BankAccount> root, BankAccountSearchParamsDTO bankAccountSearchParamsDTO);

    long count(BankAccountSearchParamsDTO bankAccountSearchParamsDTO);

    List<BankAccountListItemDTO> search(BankAccountSearchParamsDTO bankAccountSearchParamsDTO);
}
