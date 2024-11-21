package net.yazin.etebank.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import net.yazin.etebank.dto.bankAccount.BankAccountListItemDTO;
import net.yazin.etebank.dto.bankAccount.BankAccountSearchParamsDTO;
import net.yazin.etebank.entity.BankAccount;
import net.yazin.etebank.entity.BankAccount_;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BankAccountDAOimp implements BankAccountDAO {

    @Autowired private EntityManager em;

    @Override
    public List<Predicate> predicateMaker(CriteriaBuilder cb, Root<BankAccount> root, BankAccountSearchParamsDTO bankAccountSearchParamsDTO){
        List<Predicate> predicates=new ArrayList<Predicate>();

        if(bankAccountSearchParamsDTO.getBankAccountBalanceGreaterThan()!=null){
            predicates.add(cb.greaterThan(root.get(BankAccount_.BANK_ACCOUNT_BALANCE),bankAccountSearchParamsDTO.getBankAccountBalanceGreaterThan()));
        }
        if(bankAccountSearchParamsDTO.getBankAccountBalanceLessThan()!=null){
            predicates.add(cb.lessThanOrEqualTo(root.get(BankAccount_.BANK_ACCOUNT_BALANCE),bankAccountSearchParamsDTO.getBankAccountBalanceLessThan()));
        }
        if(bankAccountSearchParamsDTO.getBankAccountOwnerContains()!=null){
            predicates.add(cb.like(root.get(BankAccount_.BANK_ACCOUNT_OWNER),"%"+bankAccountSearchParamsDTO.getBankAccountOwnerContains()+"%"));
        }
        if(bankAccountSearchParamsDTO.getBankAccountNumberContains()!=null){
            predicates.add(cb.like(root.get(BankAccount_.BANK_ACCOUNT_NUMBER),"%"+bankAccountSearchParamsDTO.getBankAccountNumberContains()+"%"));
        }

        return predicates;
    }

    @Override
    public long count(BankAccountSearchParamsDTO bankAccountSearchParamsDTO){

        Session s=em.unwrap(Session.class);

        CriteriaBuilder cb=s.getCriteriaBuilder();
        CriteriaQuery<Long> cq=cb.createQuery(Long.class);
        Root<BankAccount> root=cq.from(BankAccount.class);

        cq.distinct(true);
        cq.select(cb.count(root));

        var predicates = predicateMaker(cb,root,bankAccountSearchParamsDTO);

        if(!predicates.isEmpty()){

            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        }
        return em.createQuery(cq).getSingleResult();
    }


    @Override
    public List<BankAccountListItemDTO> search(BankAccountSearchParamsDTO bankAccountSearchParamsDTO){

        Session s=em.unwrap(Session.class);

        CriteriaBuilder cb=s.getCriteriaBuilder();
        CriteriaQuery<BankAccountListItemDTO> cq=cb.createQuery(BankAccountListItemDTO.class);
        Root<BankAccount> root=cq.from(BankAccount.class);

        cq.select(cb.construct(BankAccountListItemDTO.class,
                root.get(BankAccount_.BANK_ACCOUNT_ID),
                root.get(BankAccount_.BANK_ACCOUNT_NUMBER),
                root.get(BankAccount_.BANK_ACCOUNT_OWNER),
                root.get(BankAccount_.BANK_ACCOUNT_BALANCE)
        ));


        var predicates = predicateMaker(cb,root,bankAccountSearchParamsDTO);

        if(!predicates.isEmpty()){
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        if(bankAccountSearchParamsDTO.getSort()!=null){
            cq.orderBy(bankAccountSearchParamsDTO.getSort()==BankAccountSearchParamsDTO.SORT_ASC?cb.asc(root.get(bankAccountSearchParamsDTO.getColumnName())):cb.desc(root.get(bankAccountSearchParamsDTO.getColumnName())));
        }
        return em.createQuery(cq).setFirstResult(bankAccountSearchParamsDTO.getPageNumber()* bankAccountSearchParamsDTO.getItemCount()).setMaxResults(bankAccountSearchParamsDTO.getItemCount()).getResultList();
    }

}
