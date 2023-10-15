package com.helgesander.YourBunny.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    private Integer messageId;
    //@OneToMany(mappedBy = )
    private Integer conversationId;
    private Integer senderId;
    private Integer idOfAddressUser;
    @Column(name = "message")
    private String message;
    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;
}
