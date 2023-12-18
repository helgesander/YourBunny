package ru.yourbunny.yourbunny.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreFilter;
import ru.yourbunny.yourbunny.models.Profile;
import ru.yourbunny.yourbunny.models.User;

import java.util.List;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    @Query("SELECT p.user_id FROM profiles p WHERE p.profile_id = :profileId")
    User findUserByProfileId(@Param("profileId") UUID profileId);

    Profile findByProfileId(UUID id);
}