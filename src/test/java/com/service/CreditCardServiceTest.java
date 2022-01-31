package com.service;

import com.card.dao.CreditCardDaoImpl;
import com.card.exception.CardNotValidException;
import com.card.exception.DataNotFoundException;
import com.card.io.CreditCardIO;
import com.card.model.CreditCard;
import com.card.service.CreditCardServiceImpl;
import com.card.util.CreditCardUtil;
import com.jwt.service.CustomeUserDetailService;
import com.jwt.util.JwtUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditCardServiceImpl.class)
public class CreditCardServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardDaoImpl dao;

    @MockBean
    private CreditCardUtil cardUtil;

    @MockBean
    CustomeUserDetailService customeUserDetailService;

    @MockBean
    JwtUtil jwtUtil;

    @Autowired
    private CreditCardServiceImpl service;

    @Test
    public void testAddCard() throws Exception {
        CreditCard mockCard= new CreditCard(1,"1358954993914435","Abhijeet",150000L,0L);
        Mockito.when(dao.addCard(Mockito.any(CreditCard.class))).thenReturn(mockCard);
        CreditCard result = dao.addCard(mockCard);
        Assert.assertEquals("1358954993914435",result.getNumber());
    }

    @Test(expected = CardNotValidException.class)
    public void testInValidCardNumber() throws Exception {
        CreditCardIO cardIO= new CreditCardIO("7873732247884","Abhijeet",150000L);
        service.addCard(cardIO);
    }

    @Test(expected = CardNotValidException.class)
    public void testCardNumberLength() throws Exception {
        CreditCardIO cardIO= new CreditCardIO("7873732247884545356646","Abhijeet",150000L);
        service.addCard(cardIO);
    }

    @Test(expected = CardNotValidException.class)
    public void testCardNumberIsAlphaNumeric() throws Exception {
        CreditCardIO cardIO= new CreditCardIO("7873n45r45356646","Abhijeet",150000L);
        service.addCard(cardIO);
    }

    @Test
    public void testGetAllCards() throws Exception {
        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(new CreditCard(1,"1358954993914435","Abhijeet",150000L,0L));
        Mockito.when(dao.getAllCards()).thenReturn((List<CreditCard>) creditCards);
        List<CreditCard> cards = dao.getAllCards();
        Assert.assertEquals("1358954993914435",cards.get(0).getNumber());
    }

    @Test
    public void testCardsNotFound() throws Exception {
        try{
            Mockito.when(dao.getAllCards()).thenThrow(DataNotFoundException.class);
            dao.getAllCards();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  DataNotFoundException);
        }

    }

}
