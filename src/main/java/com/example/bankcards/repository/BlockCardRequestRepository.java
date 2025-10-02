package com.example.bankcards.repository;

import com.example.bankcards.entity.BlockCardRequest;
import com.example.bankcards.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockCardRequestRepository extends JpaRepository<BlockCardRequest, Integer> {
    List<BlockCardRequest> findAllByStatus(Status status);
}
