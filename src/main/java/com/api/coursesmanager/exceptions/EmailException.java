package com.api.coursesmanager.exceptions;

public class EmailException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EmailException(String s, Throwable e) {
        super(s, e);
    }

}
