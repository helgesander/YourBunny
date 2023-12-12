package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;

@Entity
@Table (name = "chats")
public class Chat {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String chat_id;
    //TODO: fix chats model
}
