package it.academy.api.storage;

import it.academy.entity.Message;
import java.sql.SQLException;
import java.util.List;

public interface IMessageStorage {
    void save(Message message) throws SQLException;
    List<Message> findByUser(String user) throws SQLException;
    int getTotalMessagesCount() throws SQLException;
}
