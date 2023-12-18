package ru.yourbunny.yourbunny.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name = "user_id")
    private UUID userId;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column (name = "password", nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phone", unique = true, nullable = false)
    private String phone;
    @Column(name = "enabled")
    private boolean isEnabled;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    @JsonIgnore
    private Profile profile;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    @JsonIgnore
    private List<Role> roles;
}