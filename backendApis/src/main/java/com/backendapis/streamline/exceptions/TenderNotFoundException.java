package com.backendapis.streamline.exceptions;

public class TenderNotFoundException extends RuntimeException {
    public TenderNotFoundException(String message) {
        super(message);
    }
}
