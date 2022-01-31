package com.card.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String cardNumber) {
        super("Duplicate record for cardNumber "+cardNumber);
    }
}
