package net.yazin.etebank.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class TransactionResultDTO {
    private String  status;
    private String transactionApprovalCode;
}
