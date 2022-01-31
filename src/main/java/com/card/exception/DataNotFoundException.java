package com.card.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException() {
        super("No Cards available");
    }
}
