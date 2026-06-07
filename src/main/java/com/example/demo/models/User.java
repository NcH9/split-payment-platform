package com.example.demo.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {}
}
