package com.card.service;

import com.card.dto.CreditCardDTO;
import com.card.io.CreditCardIO;

import java.util.List;

public interface CreditCardService {
    public CreditCardDTO addCard(CreditCardIO creditCardIO);
    public List<CreditCardDTO> getAllCards();
}
