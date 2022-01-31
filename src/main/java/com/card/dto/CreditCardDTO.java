package com.card.dto;

import lombok.Data;

@Data
public class CreditCardDTO {
    private String number;
    private String name;
    private long limit;
    private long balance;

    public CreditCardDTO() {
    }

    public CreditCardDTO(String number, String name, Long limit, Long balance) {
        this.number = number;
        this.name = name;
        this.limit = limit;
        this.balance = balance;
    }
}
