package net.yazin.etebank.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionDTO {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss+SSSS") LocalDateTime transactionDate;
    private double transactionAmount;

    private UUID transactionApprovalCode;

    private String transactionType;

}
