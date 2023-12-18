package ru.yourbunny.yourbunny.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.dtos.ProfileDto;
import ru.yourbunny.yourbunny.exceptions.ApplicationErrorResponse;
import ru.yourbunny.yourbunny.exceptions.ProfileNotFoundException;
import ru.yourbunny.yourbunny.models.Profile;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.security.SiteUserDetails;
import ru.yourbunny.yourbunny.services.ProfileService;

import java.util.UUID;

@RestController
@RequestMapping("/profile")
@Tag(name = "API профилей", description = "Взаимодействие с профилями пользователей (выдача рекомендаций и тд.)")
public class ProfileController {

    private ProfileService profileService;

    private User getUserFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SiteUserDetails siteUserDetails = (SiteUserDetails)authentication.getPrincipal();
        return siteUserDetails.getUser();
    }
//    @GetMapping("/{profileId}")
//    public ProfileDto getProfile(@PathVariable UUID profileId) throws ProfileNotFoundException {
//        User user = profileService.getUserByProfileId(profileId);
//        Profile profile = profileService.findById(profileId);
//        return new ProfileDto()
//    }

    @PostMapping()
    public ResponseEntity<?> addProfile(@RequestBody ProfileDto profileDto) {
        User user = getUserFromSecurityContext();
        Profile profile = new Profile();
        profile.setAge(profileDto.getAge());
        profile.setAboutMe(profileDto.getAboutMe());
        profile.setDateOfBirth(profileDto.getDateOfBirth());
        profile.setAvatar(profileDto.getAvatar());
        profile.setGender(profileDto.getGender());
        profile.setHobbies(profileDto.getHobbies());
        profileService.save(profile);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    private ResponseEntity<ApplicationErrorResponse> ProfileNotFoundHandleException(ProfileNotFoundException e) {
        return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
