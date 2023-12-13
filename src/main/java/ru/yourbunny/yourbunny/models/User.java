package ru.yourbunny.yourbunny.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_id;
    @Column(name = "username")
    @NotNull
    private String username;
    @Column (name = "password")
    @NotNull
    private String password;
    @Column(name = "email")
    @NotNull
    private String email;
    @Column(name = "phone")
    @NotNull
    private String phone;
    @Column(name = "role")
    @NotNull
    private String role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    @JsonIgnore
    private Profile profile;
//    @ManyToMany
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    private Collection<Role> roles;
}