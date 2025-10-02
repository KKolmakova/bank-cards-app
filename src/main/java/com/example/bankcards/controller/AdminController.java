package com.example.bankcards.controller;

import com.example.bankcards.dto.BlockCardDTO;
import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.service.AdminCardService;
import com.example.bankcards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminCardService adminCardService;
    private final UserService userService;

    @PostMapping("/cards")
    public ResponseEntity<CardDTO> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(adminCardService.createCard(card));
    }

    @PutMapping("/cards/{cardId}/activate")
    public ResponseEntity<CardDTO> activateCard(@PathVariable Integer cardId) {
        return ResponseEntity.ok(adminCardService.activateCard(cardId));
    }

    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable Integer cardId) {
        return ResponseEntity.ok(adminCardService.deleteCard(cardId));
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardDTO>> getAllCards() {
        return ResponseEntity.ok(adminCardService.getAllCards());
    }

    @GetMapping("/block-requests")
    public ResponseEntity<List<BlockCardDTO>> getBlockRequests(
            @RequestParam(value = "status", required = false) String status) {
        List<BlockCardDTO> requests = "pending".equalsIgnoreCase(status)
                ? adminCardService.getPendingBlockRequests()
                : adminCardService.getAllBlockRequests();

        return ResponseEntity.ok(requests);
    }

    @PostMapping("/block-requests/{requestId}/approve")
    public ResponseEntity<String> approveRequest(@PathVariable Integer requestId) {
        return ResponseEntity.ok(adminCardService.approveBlockRequest(requestId));
    }

    @PostMapping("/block-requests/{requestId}/reject")
    public ResponseEntity<String> rejectRequest(@PathVariable Integer requestId) {
        return ResponseEntity.ok(adminCardService.rejectBlockRequest(requestId));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}