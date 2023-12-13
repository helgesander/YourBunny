package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "chats")
@Data
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String chat_id;
    //TODO: fix chats model
}
