package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;

@Entity
@Table (name = "roles")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long roleId;
}
