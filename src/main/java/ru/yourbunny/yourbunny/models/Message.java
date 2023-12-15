package ru.yourbunny.yourbunny.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column (name = "message_text")
    private String messageText;
    @Column (name = "message_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date messageTime;
    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private Chat ownerChat;
}
