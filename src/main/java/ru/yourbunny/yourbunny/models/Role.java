package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id"))
    private List<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }
}
