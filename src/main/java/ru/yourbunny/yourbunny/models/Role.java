package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "roles")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long roleId;
}
