package ru.yourbunny.yourbunny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourbunny.yourbunny.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
