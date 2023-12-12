package ru.yourbunny.yourbunny.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourbunny.yourbunny.models.Chat;
import ru.yourbunny.yourbunny.repositories.ChatRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;
    @GetMapping("/{chat_id}")
    public ResponseEntity<Chat> getChat(@PathVariable UUID chat_id) {
        Optional<Chat> chat = chatRepository.findById(chat_id);
        return chat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
