package it.academy.service;

import it.academy.api.service.IMessageService;
import it.academy.dto.MessageDto;
import it.academy.entity.Message;
import it.academy.mapper.MessageMapper;
import it.academy.storage.MessageStorage;
import it.academy.validation.MessageValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MessageService implements IMessageService {
    private final MessageStorage messageStorage;

    public MessageService(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public void sendMessage(MessageDto messageDto) throws SQLException {
        MessageValidator.validate(messageDto);

        Message message = MessageMapper.toEntity(messageDto); // Использование маппера для преобразования DTO в сущность
        messageStorage.save(message);
    }

    @Override
    public List<MessageDto> getMessagesByUser(String user) throws SQLException {
        List<Message> messages = messageStorage.findByUser(user);
        return messages.stream()
                .map(MessageMapper::toDto) // Использование маппера для преобразования сущности в DTO
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalMessagesCount() throws SQLException {
        return messageStorage.getTotalMessagesCount();
    }
}