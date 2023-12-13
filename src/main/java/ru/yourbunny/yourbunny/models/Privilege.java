package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "privileges")
@Data
@NoArgsConstructor
public class Privilege {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long privilegeId;
}
