package ru.yourbunny.yourbunny.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yourbunny.yourbunny.models.Profile;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProfileDto {
    private String username;
    private String aboutMe;
    private Long age;
    private Date dateOfBirth;
    private String placeOfLiving;
    private String gender;
    private String hobbies;
    private byte[] avatar;

    public ProfileDto(Profile profile) {
        this.username = profile.getUser().getUsername();
        this.aboutMe = profile.getAboutMe();
        this.age = profile.getAge();
        this.dateOfBirth = profile.getDateOfBirth();
        this.placeOfLiving = profile.getPlaceOfLiving();
    }
}
