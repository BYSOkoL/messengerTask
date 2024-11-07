package it.academy.api.service;

import it.academy.dto.MessageDto;
import it.academy.entity.Message;

import java.sql.SQLException;
import java.util.List;

public interface IMessageService {
    void sendMessage(MessageDto messageDto) throws SQLException;
    List<Message> getMessagesByUser(String user) throws SQLException;
    int getTotalMessagesCount() throws SQLException;
}
