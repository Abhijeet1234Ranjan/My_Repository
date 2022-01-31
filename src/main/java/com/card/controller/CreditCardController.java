package com.card.controller;

import com.card.dto.CreditCardDTO;
import com.card.io.CreditCardIO;
import com.card.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService service;

    @PostMapping("/card")
    public CreditCardDTO addCard(@RequestBody CreditCardIO creditCardIO){

        return service.addCard(creditCardIO);
    }

    @GetMapping("/creditcards")
    public List<CreditCardDTO> getAllCards() {

        return service.getAllCards();
    }
}
