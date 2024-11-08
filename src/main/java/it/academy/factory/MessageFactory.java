package it.academy.factory;

import it.academy.dto.MessageDto;
import it.academy.entity.Message;

public class MessageFactory {
    public static Message createMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setTimestamp(messageDto.getTimestamp());
        message.setFromUser(messageDto.getFromUser());
        message.setToUser(messageDto.getToUser());
        message.setText(messageDto.getText());
        return message;
    }
}