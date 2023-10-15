package com.helgesander.YourBunny.models;

import jakarta.persistence.*;

@Entity
@Table(name = "passwords")
public class Password {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Long userId;
    @Column(name = "hash")
    private String password_hash;
    public Password() {}
    public Password(Long userId, String password_hash) {
        this.userId = userId;
        this.password_hash = password_hash;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword_hash() {
        return password_hash;
    }
}
