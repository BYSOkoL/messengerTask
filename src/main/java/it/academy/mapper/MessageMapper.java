package it.academy.mapper;

import it.academy.dto.MessageDto;
import it.academy.entity.Message;

public class MessageMapper {
    public static MessageDto toDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setTimestamp(message.getTimestamp());
        dto.setFromUser(message.getFromUser());
        dto.setToUser(message.getToUser());
        dto.setText(message.getText());
        return dto;
    }
}