package com.helgesander.YourBunny.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversations")
public class Conversation {
    @Id
    private Long conversationId;
    private String firstUserId;
    private String secondUserId;
    priva
    public Conversation() {
    }
}
