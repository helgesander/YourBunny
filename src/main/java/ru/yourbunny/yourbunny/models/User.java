package ru.yourbunny.yourbunny.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import ru.yourbunny.yourbunny.dtos.CreateUserDto;
import ru.yourbunny.yourbunny.dtos.RegistrationDto;

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
    @Email
    private String email;
    @Column(name = "phone", unique = true, nullable = false)
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;
    @Column(name = "enabled")
    private boolean isEnabled;
    @Column(name = "activation_code")
    private String activationCode;
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

    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public User(RegistrationDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.phone = userDto.getPhone();
    }
}