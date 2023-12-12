package ru.yourbunny.yourbunny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourbunny.yourbunny.models.Message;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
