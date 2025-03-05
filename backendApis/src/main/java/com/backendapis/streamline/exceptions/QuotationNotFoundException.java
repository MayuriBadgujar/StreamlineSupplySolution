package com.backendapis.streamline.exceptions;

public class QuotationNotFoundException extends RuntimeException {
    public QuotationNotFoundException(String message) {
        super(message);
    }
}
