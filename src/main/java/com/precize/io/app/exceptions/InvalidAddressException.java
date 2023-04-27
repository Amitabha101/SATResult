package com.precize.io.app.exceptions;

public class InvalidAddressException extends RuntimeException{
    public InvalidAddressException(String message) {
        super(message);
    }
}
