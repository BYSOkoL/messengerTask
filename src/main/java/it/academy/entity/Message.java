package it.academy.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Message {
    private LocalDateTime timestamp;
    private String fromUser;
    private String toUser;
    private String text;
}