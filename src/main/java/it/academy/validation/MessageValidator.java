package it.academy.validation;

import it.academy.dto.MessageDto;

public class MessageValidator {
    public static void validate(MessageDto messageDto) {
        if (messageDto.getFromUser() == null || messageDto.getFromUser().isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be empty");
        }
        if (messageDto.getToUser() == null || messageDto.getToUser().isEmpty()) {
            throw new IllegalArgumentException("Recipient cannot be empty");
        }
        if (messageDto.getText() == null || messageDto.getText().isEmpty()) {
            throw new IllegalArgumentException("Message text cannot be empty");
        }
    }
}