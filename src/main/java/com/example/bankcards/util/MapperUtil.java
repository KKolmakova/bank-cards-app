package com.example.bankcards.util;

import com.example.bankcards.dto.BlockCardDTO;
import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.BlockCardRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperUtil {

    private final CardMaskUtil cardMaskUtil;

    public CardDTO mapToCardDTO(Card card) {
        if (card == null) return null;

        return CardDTO.builder()
                .id(card.getId())
                .maskedNumber(cardMaskUtil.maskCardNumber(card.getNumber()))
                .balance(card.getBalance())
                .userId(card.getOwner().getId())
                .expire(card.getExpire())
                .statusId(card.getStatus().getId())
                .build();
    }

    public UserDTO mapToUserDTO(User user, List<Card> cards) {
        if (user == null) return null;

        List<CardDTO> cardDTOList = cards.stream()
                .map(this::mapToCardDTO)
                .collect(Collectors.toList());

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .enabled(user.getEnabled())
                .cards(cardDTOList)
                .build();
    }

    public BlockCardDTO mapToCardBlockRequestDTO(BlockCardRequest request) {
        if (request == null) return null;

        return BlockCardDTO.builder()
                .id(request.getId())
                .cardId(request.getCard().getId())
                .userId(request.getUser().getId())
                .cardNumberMasked(cardMaskUtil.maskCardNumber(request.getCard().getNumber()))
                .status(request.getStatus().getName())
                .creationDate(request.getCreationDate())
                .build();
    }
}
