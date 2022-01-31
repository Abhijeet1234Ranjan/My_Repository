package com.card.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CreditCardControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardNotValidException.class)
    public ResponseEntity<?> cardNotValidHandler(CardNotValidException ex) {
        ErrorDetails errDetails = new ErrorDetails(new Date(), ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorDetails>(errDetails,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> cardNotFoundHandler(DataNotFoundException ex) {
        ErrorDetails errDetails = new ErrorDetails(new Date(), ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorDetails>(errDetails,new HttpHeaders(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InValidInputException.class)
    public ResponseEntity<?> invalidInputHandler(InValidInputException ex) {
        ErrorDetails errDetails = new ErrorDetails(new Date(), ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorDetails>(errDetails,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> duplicateRecordHandler(DuplicateRecordException ex) {
        ErrorDetails errDetails = new ErrorDetails(new Date(), ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorDetails>(errDetails,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
}
