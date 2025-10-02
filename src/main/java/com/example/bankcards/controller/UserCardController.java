package com.example.bankcards.controller;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.TransferDTO;
import com.example.bankcards.security.CustomUserDetails;
import com.example.bankcards.service.UserCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class UserCardController {

    private final UserCardService cardService;

    @GetMapping
    public ResponseEntity<List<CardDTO>> getUserCards(
            @RequestParam(required = false, defaultValue = "") String search,
            Pageable pageable,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                cardService.getUserCards(userDetails.getId(), pageable, search)
        );
    }

    @PostMapping("/block/{cardId}")
    public ResponseEntity<String> requestBlockCard(
            @PathVariable Integer cardId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                cardService.requestBlockCard(userDetails.getId(), cardId)
        );
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestBody TransferDTO request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                cardService.transfer(userDetails.getId(), request)
        );
    }

    @GetMapping("/{cardId}/balance")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable Integer cardId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                cardService.getBalance(userDetails.getId(), cardId)
        );
    }
}