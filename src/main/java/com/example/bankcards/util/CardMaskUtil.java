package com.example.bankcards.util;

import org.springframework.stereotype.Component;

@Component
public class CardMaskUtil {
    public String maskCardNumber(String plainNumber) {
        if (plainNumber == null || plainNumber.isBlank()) return null;
        String maskedNumber = plainNumber.replaceAll("\\d(?=\\d{4})", "*");
        return maskedNumber.replaceAll(".{4}", "$0 ").trim();
    }
}

