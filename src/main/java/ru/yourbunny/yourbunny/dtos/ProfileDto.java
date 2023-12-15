package ru.yourbunny.yourbunny.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ProfileDto {
    private String aboutMe;
    private Long age;
    private byte[] avatar;
    private Date dateOfBirth;
    private String gender;
    private String hobbies;
}
