package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "advice")
@Data
@NoArgsConstructor

public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "advice_id")
    private long id;
    @Column(name = "advice", nullable = false)
    private String advice;

}
