package net.yazin.etebank.dto.bankAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BankAccountListDTO {

    private List<BankAccountListItemDTO> list;
    private Long totalDataCount;

    private int pageNumber;
    private int itemCount;

}
