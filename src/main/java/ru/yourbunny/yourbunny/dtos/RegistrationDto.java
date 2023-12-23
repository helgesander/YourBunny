package ru.yourbunny.yourbunny.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegistrationDto {
    private String username;
    @Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[^\\w\\s]|[_]).{8,}$",
            message = "Password must contain one special character, 2 digits, 2 uppercase letters")
    private String password;
    private String confirmPassword;
    @Email
    private String email;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Bad format of phone number")
    private String phone;
}
