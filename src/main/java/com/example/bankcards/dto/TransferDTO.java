package com.example.bankcards.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDTO {
    private Integer fromCardId;
    private Integer toCardId;
    private BigDecimal amount;
}
