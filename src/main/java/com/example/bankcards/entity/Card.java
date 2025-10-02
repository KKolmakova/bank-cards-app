package com.example.bankcards.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number")
    private String number;
    @Column(name = "balance")
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    @Column(name = "expire")
    private String expire;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
