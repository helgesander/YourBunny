package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "chats")
@Data
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column (name = "chat_id")
    private String id;
    @Column(name = "first_user_id")
    private UUID firstUserId;
    @Column (name = "second_user_id")
    private UUID secondUserId;
    @Column (name = "last_message_id")
    private Long lastMessageId;
    @OneToMany(mappedBy = "ownerChat")
    private List<Message> messages;
}
