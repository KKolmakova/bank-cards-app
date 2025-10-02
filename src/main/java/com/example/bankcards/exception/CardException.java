package com.example.bankcards.exception;

import com.example.bankcards.exception.code.CardErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardException extends RuntimeException {
    private final CardErrorCode code;
    private final String message;
}

