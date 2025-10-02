package com.example.bankcards.exception.handler;

import com.example.bankcards.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException ex) {
        String response = switch (ex.getCode()) {
            case USER_NOT_FOUND -> "User not found";
            case USER_NOT_FOUND_BY_ID -> "User with id " + ex.getMessage() + " not found";
            case USER_ALREADY_EXISTS -> "User with id " + ex.getMessage() + " already exists";
            case USER_NAME_OCCUPIED -> "User name '" + ex.getMessage() + "' is occupied";
            case INVALID_USER_OPERATION -> "Invalid operation for user with id " + ex.getMessage();
        };

        return ResponseEntity.badRequest().body(response);
    }
}
