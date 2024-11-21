package net.yazin.etebank.dto.bankAccount;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountSearchParamsDTO {

    private String bankAccountNumberContains;
    private String bankAccountOwnerContains;
    private Double bankAccountBalanceGreaterThan;
    private Double bankAccountBalanceLessThan;

    private int pageNumber;
    private int itemCount;

    private String columnName;
    private Integer sort;

    public static final int SORT_ASC=0;
    public static final int SORT_DESC=1;
}
