package ru.yourbunny.yourbunny.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProfileDto {
    private String username;
    private String aboutMe;
    private Long age;
    private Date dateOfBirth;
    private String gender;
    private String hobbies;
    private byte[] avatar;
}
