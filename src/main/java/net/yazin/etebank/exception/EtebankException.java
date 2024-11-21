package net.yazin.etebank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EtebankException extends RuntimeException {
    private HttpStatus httpStatus;

    public EtebankException(String message, HttpStatus httpStatus){
        super(message);
    }
}
