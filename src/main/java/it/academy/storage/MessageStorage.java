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
    public void save(Message message) throws SQLException {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO messages (timestamp, from_user, to_user, text) VALUES (?, ?, ?, ?)");
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(message.getTimestamp()));
            ps.setString(2, message.getFromUser());
            ps.setString(3, message.getToUser());
            ps.setString(4, message.getText());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Message> findByUser(String user) throws SQLException {
        List<Message> messages = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM messages WHERE from_user = ? OR to_user = ?");
            ps.setString(1, user);
            ps.setString(2, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
                message.setFromUser(rs.getString("from_user"));
                message.setToUser(rs.getString("to_user"));
                message.setText(rs.getString("text"));
                messages.add(message);
            }
        }
        return messages;
    }

    @Override
    public int getTotalMessagesCount() throws SQLException {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM messages");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}