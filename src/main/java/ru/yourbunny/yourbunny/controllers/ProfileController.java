package ru.yourbunny.yourbunny.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import ru.yourbunny.yourbunny.dtos.ProfileDto;
import ru.yourbunny.yourbunny.exceptions.ApplicationErrorResponse;
import ru.yourbunny.yourbunny.exceptions.ProfileNotFoundException;
import ru.yourbunny.yourbunny.models.Profile;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.security.SiteUserDetails;
import ru.yourbunny.yourbunny.services.ProfileService;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/profile")
@Tag(name = "API профилей", description = "Взаимодействие с профилями пользователей (выдача рекомендаций и тд.)")
public class ProfileController {

    private ProfileService profileService;

    private User getUserFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SiteUserDetails siteUserDetails = (SiteUserDetails)authentication.getPrincipal();
        return siteUserDetails.getUser();
    }
    @GetMapping("/{profileId}")
    public ProfileDto getProfile(@PathVariable UUID profileId) throws ProfileNotFoundException {
        return new ProfileDto(profileService.findById(profileId));
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileDto> addProfile(@RequestBody ProfileDto profileDto,
                                                 HttpServletRequest request) {
        User user = getUserFromSecurityContext();
        Profile profile = new Profile();
        profile.setAge(profileDto.getAge());
        profile.setAboutMe(profileDto.getAboutMe());
        profile.setDateOfBirth(profileDto.getDateOfBirth());
        profile.setAvatar(profileDto.getAvatar());
        profile.setGender(profileDto.getGender());
        profile.setHobbies(profileDto.getHobbies());
        profileService.save(profile);
        log.info("Client from {}: create new profile with id {}", request.getRemoteAddr(),
                profileService.findByUserUsername(user.getUsername()));
        return new ResponseEntity<>(new ProfileDto(profile), HttpStatus.OK);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    private ResponseEntity<ApplicationErrorResponse> ProfileNotFoundHandleException(ProfileNotFoundException e) {
        return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
