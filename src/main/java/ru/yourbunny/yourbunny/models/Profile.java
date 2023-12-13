package ru.yourbunny.yourbunny.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table (name = "profiles")
@Data
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID profileId;
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "age")
    private String age;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "gender")
    private String gender;
    @Column (name = "hobbies")
    private String hobbies;
    @Column (name = "avatar")
    private byte[] avatar;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
