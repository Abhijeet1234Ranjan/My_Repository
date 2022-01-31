package com.card.service;

import com.card.constants.ServiceConstants;
import com.card.dao.CreditCardDao;
import com.card.dto.CreditCardDTO;
import com.card.exception.CardNotValidException;
import com.card.exception.DataNotFoundException;
import com.card.exception.DuplicateRecordException;
import com.card.exception.InValidInputException;
import com.card.io.CreditCardIO;
import com.card.model.CreditCard;
import com.card.util.CreditCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardDao cardDao;

    @Autowired
    CreditCardUtil creditCardUtil;

    @Override
    public CreditCardDTO addCard(CreditCardIO creditCardIO) {
        if (checkInputs(creditCardIO)){
            String cardNumber = creditCardIO.getNumber();
            if (checkValidCardNumber(cardNumber)){
                try {
                    CreditCard card = cardDao.addCard(creditCardUtil.credtcardRequestMapping(creditCardIO));
                    return creditCardUtil.credtcardResponseMapping(card);
                }catch (Exception e){
                    throw new DuplicateRecordException(cardNumber);
                }
            }else {
                throw new CardNotValidException(cardNumber);
            }
        }else {
            throw new InValidInputException();
        }
    }

    private boolean checkInputs(CreditCardIO creditCardIO){
        return !StringUtils.isEmpty(creditCardIO.getNumber())
                && !StringUtils.isEmpty(creditCardIO.getName())
                && creditCardIO.getLimit() != 0L;
    }

    private boolean checkValidCardNumber(String cardNumber){
        return cardNumber.length() <= ServiceConstants.NINETEEN
                && checkDigit(cardNumber)
                && isNumberValid(cardNumber);
    }

    private static boolean isNumberValid(String cardNumber) {
        String[] splitNum = cardNumber.split("");
        int []numArr = Arrays.stream(splitNum).mapToInt(Integer::parseInt).toArray();
        for(int i=numArr.length-2;i>=0;i=i-2) {
            int num = numArr[i]*2;
            if(num>9){
                num = num%10 + num/10;
            }
            numArr[i]=num;
        }
        return sumDigits(numArr) % 10 == 0;
    }

    private static int sumDigits(int[] arr) {
        return Arrays.stream(arr).sum();
    }

    private static boolean checkDigit(String cardNumber){
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if (cardNumber == null) {
            return false;
        }
        Matcher m = p.matcher(cardNumber);
        return m.matches();
    }

    @Override
    public List<CreditCardDTO> getAllCards() {
        List<CreditCard> cards = cardDao.getAllCards();
        if (CollectionUtils.isEmpty(cards)){
            throw new DataNotFoundException();
        }
        return creditCardUtil.mappingCreditCardListToDto(cards);
    }


}
