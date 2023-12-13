package ru.yourbunny.yourbunny.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourbunny.yourbunny.models.Profile;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.security.SiteUserDetails;

@RestController
@RequestMapping("/home")
public class ProfileController {
    @GetMapping()
    public Profile getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SiteUserDetails siteUserDetails = (SiteUserDetails)authentication.getPrincipal();
        User currentUser = siteUserDetails.getUser();
        Profile profile = currentUser.getProfile();
        return profile;
    }
}
