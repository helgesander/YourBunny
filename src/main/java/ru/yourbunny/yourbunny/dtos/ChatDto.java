package ru.yourbunny.yourbunny.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatDto {
    private UUID id;
    // TODO: fix

    public ChatDto(UUID id) {
        this.id = id;
    }
}
