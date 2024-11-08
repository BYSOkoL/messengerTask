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

    public static Message toEntity(MessageDto dto) {
        Message message = new Message();
        message.setTimestamp(dto.getTimestamp());
        message.setFromUser(dto.getFromUser());
        message.setToUser(dto.getToUser());
        message.setText(dto.getText());
        return message;
    }
}