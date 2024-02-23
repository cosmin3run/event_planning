package com.cosminpetrea.event_planning.exceptions;

import com.cosminpetrea.event_planning.exceptions.payloads.ErrorDTO;
import com.cosminpetrea.event_planning.exceptions.payloads.ErrorDTOWithList;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTOWithList handleBadRequest(BadRequestException exception){
        List<String> errorMessage = new ArrayList<>();
        if (exception.getErrorList() != null)
            errorMessage = exception.getErrorList().stream().map(error -> error.getDefaultMessage()).toList();
        return new ErrorDTOWithList(exception.getMessage(), LocalDate.now(), errorMessage);


    }
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleUnauthorized(UnauthorizedException exception ){
        return new ErrorDTO(exception.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorDTO handleAccessDenied(AccessDeniedException ex) {
        return new ErrorDTO("Your role has no access to this feature", LocalDate.now());
    }

    @ExceptionHandler(NotFoundExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(NotFoundExceptions e){
        return new ErrorDTO(e.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGenericProblem(Exception e){
        e.printStackTrace();
        return new ErrorDTO("Something's off, we'll check as soon as possible", LocalDate.now());
    }
}
