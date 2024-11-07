package it.academy.validation;

import it.academy.dto.MessageDto;

public class MessageValidator {

    public static boolean isValid(MessageDto messageDto) {
        return isValidText(messageDto.getText()) && isValidUser(messageDto.getFromUser()) && isValidUser(messageDto.getToUser());
    }

    private static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    private static boolean isValidUser(String user) {
        return user != null && !user.trim().isEmpty();
    }
}