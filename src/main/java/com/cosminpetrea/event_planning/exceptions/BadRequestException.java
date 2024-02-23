package com.cosminpetrea.event_planning.exceptions;

import lombok.Getter;

import org.springframework.validation.ObjectError;
import java.util.List;

@Getter

public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorList;

    public BadRequestException(String message){super(message);}

    public BadRequestException(List<ObjectError> errorList){
        super("Error. Check the body and retry");
        this.errorList = errorList;
    }
}
