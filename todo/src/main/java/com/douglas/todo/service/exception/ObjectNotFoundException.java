package com.douglas.todo.service.exception;

public class ObjectNotFoundException extends RuntimeException{
    private  static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ObjectNotFoundException(String s) {
        super(s);
    }
}
