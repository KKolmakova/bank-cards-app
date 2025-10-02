package com.example.bankcards.exception;

import com.example.bankcards.exception.code.UserErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final UserErrorCode code;
    private final String message;
}

