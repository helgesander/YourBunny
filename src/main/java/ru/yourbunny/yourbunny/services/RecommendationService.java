package ru.yourbunny.yourbunny.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yourbunny.yourbunny.models.Profile;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.ProfileRepository;

import java.util.List;


@Service
public class RecommendationService {
    @Autowired
    private ProfileRepository profileRepository;
//    public List<Profile> findMatchingProfiles(User currentUser) {
//        List<Profile> profiles = null;
//        // TODO: fix find recomendations for users
//        return profiles;
//    }
}
