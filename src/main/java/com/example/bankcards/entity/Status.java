package com.example.bankcards.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
}
