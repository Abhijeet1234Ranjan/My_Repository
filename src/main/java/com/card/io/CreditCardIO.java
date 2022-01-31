package com.card.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardIO {
    private String number;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long limit;

    public CreditCardIO() {
    }

    public CreditCardIO(String number, String name, Long limit) {
        this.number = number;
        this.name = name;
        this.limit = limit;
    }
}
