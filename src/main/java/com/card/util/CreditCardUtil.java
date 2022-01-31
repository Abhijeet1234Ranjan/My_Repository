package com.card.util;

import com.card.dto.CreditCardDTO;
import com.card.io.CreditCardIO;
import com.card.model.CreditCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CreditCardUtil {

    public CreditCardDTO credtcardResponseMapping(CreditCard creditCard){
        CreditCardDTO cardDTO = new CreditCardDTO();
        cardDTO.setNumber(creditCard.getNumber());
        cardDTO.setName(creditCard.getName());
        cardDTO.setLimit(creditCard.getLimit());
        cardDTO.setBalance(creditCard.getBalance());
        return cardDTO;
    }

    public CreditCard credtcardRequestMapping(CreditCardIO creditCardIO){
        CreditCard card = new CreditCard();
        card.setNumber(creditCardIO.getNumber());
        card.setName(creditCardIO.getName());
        card.setLimit(creditCardIO.getLimit());
        return card;
    }

    public List<CreditCardDTO> mappingCreditCardListToDto(List<CreditCard> cards){

        return cards.stream().map(card ->
                        new CreditCardDTO(card.getNumber(), card.getName(), card.getLimit(), card.getBalance()))
                .collect(Collectors.toList());
    }
}
