package it.academy.service;

import it.academy.api.service.IMessageService;
import it.academy.api.storage.IMessageStorage;
import it.academy.dto.MessageDto;
import it.academy.entity.Message;
import it.academy.storage.MessageStorage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MessageService implements IMessageService {

    private final IMessageStorage messageStorage;

    public MessageService(IMessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public void sendMessage(MessageDto messageDto) throws SQLException {
        Message message = Message.builder()
                .timestamp(LocalDateTime.now())
                .fromUser(messageDto.getFromUser())
                .toUser(messageDto.getToUser())
                .text(messageDto.getText())
                .build();
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
