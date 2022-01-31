package com.card;

import com.card.controller.CreditCardController;
import com.card.dto.CreditCardDTO;
import com.card.dto.ResponseDTO;
import com.card.exception.CardNotValidException;
import com.card.io.CreditCardIO;
import com.card.service.CreditCardService;
import com.jwt.service.CustomeUserDetailService;
import com.jwt.util.JwtUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditCardController.class)
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardService creditCardService;

    @MockBean
    CustomeUserDetailService customeUserDetailService;

    @MockBean
    JwtUtil jwtUtil;

    @Test
    public void testAddCard() throws Exception {
        String inputJson = "{\"number\":\"1358954993914435\",\"name\":\"Abhijeet\",\"limit\":150000}";
        String outputJson = "{\"responseCode\":\"OK\",\"responseMessage\":\"Success\"}";
        ResponseDTO mockResponse = new ResponseDTO("OK","Success");
        Mockito.when(creditCardService.addCard(Mockito.any(CreditCardIO.class))).thenReturn(mockResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/card")
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        JSONAssert.assertEquals(outputJson,response.getContentAsString(),true);
        Assert.assertEquals(200,response.getStatus());
    }

    @Test
    public void testAddingInvalidCard() throws Exception {
        String inputJson = "{\"number\":\"7873732247884\",\"name\":\"Abhijeet\",\"limit\":150000}";
        Mockito.when(creditCardService.addCard(Mockito.any(CreditCardIO.class))).thenThrow(CardNotValidException.class);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/card")
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(400,response.getStatus());
    }

    @Test
    public void testgettingAllCards() throws Exception {

        String outputJson = "[{\"number\":\"1358954993914435\",\"name\":\"Abhijeet\",\"limit\":150000,\"balance\":0},{\"number\":\"1212665653333353\",\"name\":\"Abhijeet123\",\"limit\":150000,\"balance\":0}]";
        List<CreditCardDTO> creditCardDTOs = new ArrayList<>();
        creditCardDTOs.add(new CreditCardDTO("1358954993914435","Abhijeet",150000L,0L));
        creditCardDTOs.add(new CreditCardDTO("1212665653333353","Abhijeet123",150000L,0L));
        Mockito.when(creditCardService.getAllCards()).thenReturn(creditCardDTOs);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/creditcards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(200,response.getStatus());
        Assert.assertEquals(outputJson,response.getContentAsString());
    }

}
