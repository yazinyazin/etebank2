package net.yazin.etebank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EtebankHandler {
    @ExceptionHandler(EtebankException.class)
    protected ResponseEntity<EtebankErrorMessage> basicHandler(EtebankException ex){
        return new ResponseEntity<>(EtebankErrorMessage.builder().message(ex.getMessage()).status("FAILED").build(), ex.getHttpStatus());
    }
}
