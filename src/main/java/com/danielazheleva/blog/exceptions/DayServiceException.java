package com.danielazheleva.blog.exceptions;

public class DayServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DayServiceException(String message) {
        super(message);
    }
}
