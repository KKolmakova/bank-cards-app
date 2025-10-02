package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.TransferDTO;
import com.example.bankcards.entity.BlockCardRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Status;
import com.example.bankcards.exception.CardException;
import com.example.bankcards.exception.code.CardErrorCode;
import com.example.bankcards.repository.BlockCardRequestRepository;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserCardService {

    private final CardRepository cardRepository;
    private final BlockCardRequestRepository blockCardRequestRepository;
    private final MapperUtil mapperUtil;
    private final Map<Integer, Status> statusMap;

    @Transactional(readOnly = true)
    public List<CardDTO> getUserCards(Integer userId, Pageable pageable, String search) {
        return cardRepository.findByOwnerIdAndNumberContaining(userId, search, pageable)
                .map(mapperUtil::mapToCardDTO)
                .getContent();
    }

    @Transactional
    public String requestBlockCard(Integer userId, Integer cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardErrorCode.CARD_NOT_FOUND, cardId.toString()));

        if (!card.getOwner().getId().equals(userId)) {
            throw new CardException(CardErrorCode.CARD_NOT_FOUND, cardId.toString());
        }

        if (card.getStatus().getId().equals(2)) {
            throw new CardException(CardErrorCode.CARD_BLOCKED, cardId.toString());
        }

        BlockCardRequest request = BlockCardRequest.builder()
                .card(card)
                .user(card.getOwner())
                .status(statusMap.get(4))
                .creationDate(LocalDateTime.now())
                .build();

        blockCardRequestRepository.save(request);
        return "Card block request has been sent and is pending approval.";
    }

    @Transactional
    public String transfer(Integer userId, TransferDTO request) {
        Card fromCard = cardRepository.findByIdAndOwnerId(request.getFromCardId(), userId)
                .orElseThrow(() -> new CardException(CardErrorCode.CARD_NOT_FOUND, request.getFromCardId().toString()));

        Card toCard = cardRepository.findByIdAndOwnerId(request.getToCardId(), userId)
                .orElseThrow(() -> new CardException(CardErrorCode.CARD_NOT_FOUND, request.getToCardId().toString()));

        if (fromCard.getBalance().compareTo(request.getAmount()) < 0) {
            throw new CardException(CardErrorCode.INSUFFICIENT_FUNDS, request.getFromCardId().toString());
        }

        fromCard.setBalance(fromCard.getBalance().subtract(request.getAmount()));
        toCard.setBalance(toCard.getBalance().add(request.getAmount()));

        cardRepository.save(fromCard);
        cardRepository.save(toCard);

        return "Transfer completed successfully.";
    }

    public BigDecimal getBalance(Integer userId, Integer cardId) {
        Card card = cardRepository.findByIdAndOwnerId(cardId, userId)
                .orElseThrow(() -> new CardException(CardErrorCode.CARD_NOT_FOUND, cardId.toString()));
        return card.getBalance();
    }
}