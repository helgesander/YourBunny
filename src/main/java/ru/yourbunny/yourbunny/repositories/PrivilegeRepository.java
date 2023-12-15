package ru.yourbunny.yourbunny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourbunny.yourbunny.models.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
