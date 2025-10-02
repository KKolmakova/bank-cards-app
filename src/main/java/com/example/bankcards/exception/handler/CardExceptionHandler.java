package com.example.bankcards.exception.handler;

import com.example.bankcards.exception.CardException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CardExceptionHandler {
    @ExceptionHandler(CardException.class)
    public ResponseEntity<String> handleCardException(CardException ex) {
        String response = switch (ex.getCode()) {
            case CARD_NOT_FOUND -> "Card with id " + ex.getMessage() + " not found";
            case INSUFFICIENT_FUNDS -> "Insufficient funds on the card with id " + ex.getMessage();
            case INVALID_TRANSFER -> "Invalid transfer operation for card id " + ex.getMessage();
            case CARD_BLOCKED -> "Card with id " + ex.getMessage() + " is already blocked";
            case CARD_ACTIVE -> "Card with id " + ex.getMessage() + " is already active";
            case REQUEST_NOT_FOUND -> "Block request with id " + ex.getMessage() + " not found";
            case INVALID_REQUEST_STATUS -> "Block request with id " + ex.getMessage() + " has invalid status";
            case CARD_ALREADY_EXISTS -> "Card with number " + ex.getMessage() + " is already exists";
        };

        return ResponseEntity.badRequest().body(response);
    }
}
