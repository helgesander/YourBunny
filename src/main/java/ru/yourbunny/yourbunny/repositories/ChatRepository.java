package ru.yourbunny.yourbunny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourbunny.yourbunny.models.Chat;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    void deleteChatById(UUID id);
}
