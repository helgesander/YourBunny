package ru.yourbunny.yourbunny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreFilter;
import ru.yourbunny.yourbunny.models.Profile;

import java.util.List;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

}
