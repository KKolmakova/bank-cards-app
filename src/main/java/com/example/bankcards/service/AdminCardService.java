package com.example.bankcards.service;

import com.example.bankcards.dto.BlockCardDTO;
import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.entity.BlockCardRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Status;
import com.example.bankcards.exception.CardException;
import com.example.bankcards.exception.code.CardErrorCode;
import com.example.bankcards.repository.BlockCardRequestRepository;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCardService {

    private final CardRepository cardRepository;
    private final BlockCardRequestRepository blockCardRequestRepository;
    private final MapperUtil mapperUtil;
    private final Map<Integer, Status> statusMap;

    @Transactional(readOnly = true)
    public List<CardDTO> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(mapperUtil::mapToCardDTO)
                .toList();
    }

    @Transactional
    public CardDTO createCard(Card card) {
        if (cardRepository.existsByNumber(card.getNumber())) {
            throw new CardException(CardErrorCode.CARD_ALREADY_EXISTS, card.getNumber());
        }

        return mapperUtil.mapToCardDTO(cardRepository.save(card));
    }

    @Transactional
    public CardDTO activateCard(Integer cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardErrorCode.CARD_NOT_FOUND, cardId.toString()));

        if (card.getStatus().getId().equals(1)) {
            throw new CardException(CardErrorCode.CARD_ACTIVE, cardId.toString());
        }

        card.setStatus(statusMap.get(1));
        return mapperUtil.mapToCardDTO(cardRepository.save(card));
    }

    @Transactional
    public String deleteCard(Integer cardId) {
        if (!cardRepository.existsById(cardId)) {
            throw new CardException(CardErrorCode.CARD_NOT_FOUND, cardId.toString());
        }
        cardRepository.deleteById(cardId);

        return "Card successfully deleted";
    }

    @Transactional(readOnly = true)
    public List<BlockCardDTO> getAllBlockRequests() {
        return blockCardRequestRepository.findAll()
                .stream()
                .map(mapperUtil::mapToCardBlockRequestDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BlockCardDTO> getPendingBlockRequests() {
        return blockCardRequestRepository.findAllByStatus(statusMap.get(4))
                .stream()
                .map(mapperUtil::mapToCardBlockRequestDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public String approveBlockRequest(Integer requestId) {
        BlockCardRequest request = blockCardRequestRepository.findById(requestId)
                .orElseThrow(() -> new CardException(CardErrorCode.REQUEST_NOT_FOUND, requestId.toString()));

        if (!request.getStatus().getId().equals(4)) {
            throw new CardException(CardErrorCode.INVALID_REQUEST_STATUS, requestId.toString());
        }

        Card card = request.getCard();
        card.setStatus(statusMap.get(2));
        request.setStatus(statusMap.get(5));

        cardRepository.save(card);
        blockCardRequestRepository.save(request);

        return "Card blocked successfully";
    }

    @Transactional
    public String rejectBlockRequest(Integer requestId) {
        BlockCardRequest request = blockCardRequestRepository.findById(requestId)
                .orElseThrow(() -> new CardException(CardErrorCode.REQUEST_NOT_FOUND, requestId.toString()));

        request.setStatus(statusMap.get(6));
        blockCardRequestRepository.save(request);

        return "Card block request has been rejected";
    }
}