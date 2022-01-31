package com.card.dao;

import com.card.model.CreditCard;

import java.util.List;

public interface CreditCardDao {
    public CreditCard addCard(CreditCard creditCard);
    public List<CreditCard> getAllCards();
}
