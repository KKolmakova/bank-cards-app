package com.example.bankcards.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private Integer id;
    private String name;
    private Boolean enabled;
    private List<CardDTO> cards;
}
