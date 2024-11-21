package net.yazin.etebank.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EtebankErrorMessage {
    private String status;
    private String message;
}
