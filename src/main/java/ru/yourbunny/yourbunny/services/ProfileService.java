package ru.yourbunny.yourbunny.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yourbunny.yourbunny.models.Profile;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.ProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    @Transactional
    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    @Transactional
    public User getUserByProfileId(UUID profileId) {
        return profileRepository.findUserByProfileId(profileId);
    }

    @Transactional
    public List<Profile> getRecommendations(Profile profile) {
        //TODO: fix recommendations
        return new ArrayList<Profile>();
    }

    @Transactional
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }
}
