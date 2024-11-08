package it.academy.api.service;

import it.academy.dto.MessageDto;
import java.sql.SQLException;
import java.util.List;

public interface IMessageService {
    void sendMessage(MessageDto messageDto) throws SQLException;
    List<MessageDto> getMessagesByUser(String user) throws SQLException; // Исправлено: возвращает List<MessageDto>
    int getTotalMessagesCount() throws SQLException;
}