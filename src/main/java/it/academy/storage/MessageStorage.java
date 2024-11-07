package it.academy.storage;

import it.academy.api.storage.IMessageStorage;
import it.academy.entity.Message;
import it.academy.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageStorage implements IMessageStorage {

    @Override
    public synchronized void save(Message message) throws SQLException {
        String sql = "INSERT INTO messages (timestamp, from_user, to_user, text) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, java.sql.Timestamp.valueOf(message.getTimestamp()));
            statement.setString(2, message.getFromUser());
            statement.setString(3, message.getToUser());
            statement.setString(4, message.getText());

            statement.executeUpdate();
        }
    }

    @Override
    public List<Message> findByUser(String user) throws SQLException {
        String sql = "SELECT * FROM messages WHERE from_user = ? OR to_user = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user);
            statement.setString(2, user);
            ResultSet resultSet = statement.executeQuery();

            List<Message> messages = new ArrayList<>();
            while (resultSet.next()) {
                Message message = Message.builder()
                        .timestamp(resultSet.getTimestamp("timestamp").toLocalDateTime())
                        .fromUser(resultSet.getString("from_user"))
                        .toUser(resultSet.getString("to_user"))
                        .text(resultSet.getString("text"))
                        .build();
                messages.add(message);
            }
            return messages;
        }
    }

    @Override
    public int getTotalMessagesCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM messages";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        }
        return 0;
    }
}
