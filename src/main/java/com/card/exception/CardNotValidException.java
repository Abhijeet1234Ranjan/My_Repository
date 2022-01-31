package com.card.exception;

public class CardNotValidException extends RuntimeException{
    public CardNotValidException(String number) {
        super("Card number " +number+ " is invalid ");
    }
}
