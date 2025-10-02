package com.example.bankcards.config;

import com.example.bankcards.entity.Status;
import com.example.bankcards.repository.StatusRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {
    @Bean
    public Map<Integer, Status> statusMap(StatusRepository statusRepository) {
        return statusRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Status::getId, s -> s));
    }
}
