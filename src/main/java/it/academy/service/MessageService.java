package it.academy.service;

import it.academy.api.service.IMessageService;
import it.academy.dto.MessageDto;
import it.academy.entity.Message;
import it.academy.storage.MessageStorage;
import it.academy.validation.MessageValidator;

import java.sql.SQLException;
import java.util.List;

public class MessageService implements IMessageService {
    private final MessageStorage messageStorage;

    public MessageService(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public void sendMessage(MessageDto messageDto) throws SQLException {
        MessageValidator.validate(messageDto);

        Message message = new Message();
        message.setFromUser(messageDto.getFromUser());
        message.setToUser(messageDto.getToUser());
        message.setText(messageDto.getText());
        message.setTimestamp(messageDto.getTimestamp());
        messageStorage.save(message);
    }

    @Override
    public List<Message> getMessagesByUser(String user) throws SQLException {
        return messageStorage.findByUser(user);
    }

    @Override
    public int getTotalMessagesCount() throws SQLException {
        return messageStorage.getTotalMessagesCount();
    }
}