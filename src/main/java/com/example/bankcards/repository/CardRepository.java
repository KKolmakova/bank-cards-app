package com.example.bankcards.repository;

import com.example.bankcards.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Page<Card> findByOwnerIdAndNumberContaining(Integer ownerId, String number, Pageable pageable);

    Optional<Card> findByIdAndOwnerId(Integer id, Integer ownerId);

    boolean existsByNumber(String number);
}
