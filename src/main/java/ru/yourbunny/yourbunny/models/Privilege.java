package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;

@Entity
@Table (name = "privileges")
public class Privilege {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long privilegeId;
}
