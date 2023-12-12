package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity
@Table (name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID profileId;
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "age")
    private String age;
    @Column(name = "date_of_born")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateOfBorn;
    @Column(name = "gender")
    @NotNull
    private String gender;
    @Column (name = "hobbies")
    private String hobbies;
    @Column (name = "avatar")
    private byte[] avatar;

}
