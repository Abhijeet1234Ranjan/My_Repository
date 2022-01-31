package com.card.dao;

import com.card.model.CreditCard;
import com.card.service.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreditCardDaoImpl implements CreditCardDao{

    @Autowired
    private CreditCardRepository repository;

    @Override
    public CreditCard addCard(CreditCard creditCard) {
        return repository.save(creditCard);
    }

    @Override
    public List<CreditCard> getAllCards() {
        return repository.findAll();
    }
}
