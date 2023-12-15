package ru.yourbunny.yourbunny.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourbunny.yourbunny.models.Profile;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.security.SiteUserDetails;
import ru.yourbunny.yourbunny.services.ProfileService;
import ru.yourbunny.yourbunny.utils.ProfileErrorResponse;
import ru.yourbunny.yourbunny.utils.ProfileNotFoundException;

@RestController
@RequestMapping("/home")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping()
    public Profile getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SiteUserDetails siteUserDetails = (SiteUserDetails)authentication.getPrincipal();
        User currentUser = siteUserDetails.getUser();
        return currentUser.getProfile();
    }

    @ExceptionHandler
    private ResponseEntity<ProfileErrorResponse> handleException(ProfileNotFoundException e) {
        ProfileErrorResponse response = new ProfileErrorResponse(
                "Profile with this id was not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
