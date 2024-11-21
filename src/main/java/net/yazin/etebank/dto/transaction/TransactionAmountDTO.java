package net.yazin.etebank.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionAmountDTO {
    private double transactionAmount;
}
