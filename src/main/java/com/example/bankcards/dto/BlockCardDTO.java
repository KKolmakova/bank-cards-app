package com.example.bankcards.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlockCardDTO {
    private Integer id;
    private Integer cardId;
    private Integer userId;
    private String cardNumberMasked;
    private String status;
    private LocalDateTime creationDate;
}
