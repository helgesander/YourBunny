package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chats")
@Data
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column(name = "chat_id")
    private UUID id;
    @Column(name = "first_user_id", nullable = false)
    private UUID firstUserId;
    @Column(name = "second_user_id", nullable = false)
    private UUID secondUserId;
    @Column(name = "last_message_id")
    private Long lastMessageId;
    @OneToMany(mappedBy = "ownerChat")
    private List<Message> messages;
}
