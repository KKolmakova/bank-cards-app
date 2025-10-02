package com.example.bankcards.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;
}
