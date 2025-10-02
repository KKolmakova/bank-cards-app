package com.example.bankcards.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CardDTO {
    private Integer id;
    private String maskedNumber;
    private BigDecimal balance;
    private Integer userId;
    private String expire;
    private Integer statusId;
}