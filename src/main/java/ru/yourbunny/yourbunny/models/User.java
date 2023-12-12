package ru.yourbunny.yourbunny.models;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_id;
    @Column(name = "username")
    @NotNull
    private String firstName;
    @Column(name = "email")
    @NotNull
    private String email;
    @Column(name = "phone")
    @NotNull
    private String phone;
    @Column(name = "role")
    @NotNull
    private String role;

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) { this.role = role; }
}