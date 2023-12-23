package ru.yourbunny.yourbunny.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.models.Chat;
import ru.yourbunny.yourbunny.services.ChatService;
import ru.yourbunny.yourbunny.services.CustomUserDetailsService;

import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "API чатов", description = "Диалог с другими пользователями")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private CustomUserDetailsService userService;

    private SimpMessagingTemplate messagingTemplate;
    @GetMapping("/{chat_id}")
    public Chat getChat(@PathVariable UUID chat_id) {
        return chatService.getById(chat_id);
    }

//    @PostMapping()
//    public ResponseEntity<?> addChat(@RequestParam String username) {
//        Chat chat = new Chat();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String usernameOfCurrentUser = (String) authentication.getPrincipal();
//        User currentUser = userService.findByUsername(usernameOfCurrentUser).orElseThrow(() -> new UserNotFoundException(usernameOfCurrentUser));
//        User secondUser = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
//        chat.setFirstUserId(currentUser.getUserId());
//        chat.setSecondUserId(secondUser.getUserId());
//        chatService.save(chat);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

//    @MessageMapping("/chat")
}
