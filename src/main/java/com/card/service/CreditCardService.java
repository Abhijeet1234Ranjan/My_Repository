package com.card.service;

import com.card.dto.CreditCardDTO;
import com.card.io.CreditCardIO;

import java.sql.SQLException;
import java.util.List;

public interface CreditCardService {
    public CreditCardDTO addCard(CreditCardIO creditCardIO) throws SQLException;
    public List<CreditCardDTO> getAllCards();
}
