package com.cosminpetrea.event_planning.exceptions;

import org.aspectj.weaver.ast.Not;

import java.util.UUID;

public class NotFoundExceptions extends RuntimeException {

    public NotFoundExceptions(UUID id){super("Element with id '"+ id + "' has not been found");}
    public NotFoundExceptions(String message){super(message);}
}
