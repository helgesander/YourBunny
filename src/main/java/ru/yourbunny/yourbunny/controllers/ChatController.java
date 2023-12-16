package ru.yourbunny.yourbunny.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.exceptions.NotFoundException;
import ru.yourbunny.yourbunny.models.Chat;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.ChatRepository;
import ru.yourbunny.yourbunny.security.SiteUserDetails;
import ru.yourbunny.yourbunny.services.ChatService;
import ru.yourbunny.yourbunny.services.CustomUserDetailsService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "API чатов", description = "Диалог с другими пользователями")
public class ChatController {
    private ChatService chatService;
    private CustomUserDetailsService userService;
    @GetMapping("/{chat_id}")
    public Chat getChat(@PathVariable UUID chat_id) {
        return chatService.getById(chat_id);
    }

    @PostMapping()
    public ResponseEntity<?> addChat(@RequestParam String username) {
        Chat chat = new Chat();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SiteUserDetails siteUserDetails = (SiteUserDetails)authentication.getPrincipal();
        User currentUser = siteUserDetails.getUser();
        User secondUser = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        chat.setFirstUserId(currentUser.getUserId());
        chat.setSecondUserId(secondUser.getUserId());
        chatService.save(chat);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
