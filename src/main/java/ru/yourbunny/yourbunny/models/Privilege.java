package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "privileges")
@Data
@NoArgsConstructor
public class Privilege {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long privilegeId;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    public Privilege(String name) {
        this.name = name;
    }
}
