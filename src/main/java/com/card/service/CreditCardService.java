package com.card.service;

import com.card.dto.CreditCardDTO;
import com.card.dto.ResponseDTO;
import com.card.io.CreditCardIO;

import java.util.List;

public interface CreditCardService {
    public ResponseDTO addCard(CreditCardIO creditCardIO);
    public List<CreditCardDTO> getAllCards();
}
