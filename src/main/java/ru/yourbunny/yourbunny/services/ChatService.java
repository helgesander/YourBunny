package ru.yourbunny.yourbunny.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.yourbunny.yourbunny.models.Chat;
import ru.yourbunny.yourbunny.repositories.ChatRepository;

import java.util.UUID;

@Service
public class ChatService {
    private ChatRepository chatRepository;

    @Transactional
    public void save(Chat chat) {
        chatRepository.save(chat);
    }

    public Chat getById(UUID chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }
}
