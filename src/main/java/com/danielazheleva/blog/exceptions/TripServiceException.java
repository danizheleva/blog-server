package com.danielazheleva.blog.exceptions;

public class TripServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TripServiceException(String message) {
        super(message);
    }
}
