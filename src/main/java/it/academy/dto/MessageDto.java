package it.academy.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageDto {
    private LocalDateTime timestamp;
    private String fromUser;
    private String toUser;
    private String text;
}