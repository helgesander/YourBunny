package ru.yourbunny.yourbunny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourbunny.yourbunny.models.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
