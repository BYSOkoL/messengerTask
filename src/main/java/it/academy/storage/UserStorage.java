package it.academy.storage;

import it.academy.api.storage.IUserStorage;
import it.academy.entity.Role;
import it.academy.entity.User;
import it.academy.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserStorage implements IUserStorage {
    @Override
    public void save(User user) throws SQLException {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (login, password, full_name, birth_date, role) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setDate(4, java.sql.Date.valueOf(user.getBirthDate()));
            ps.setString(5, user.getRole().name());
            ps.executeUpdate();
        }
    }

    @Override
    public Optional<User> findByRole(Role role) throws SQLException {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE role = ?");
            ps.setString(1, role.name());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setBirthDate(rs.getDate("birth_date").toLocalDate());
                user.setRole(Role.valueOf(rs.getString("role")));
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) throws SQLException {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setBirthDate(rs.getDate("birth_date").toLocalDate());
                user.setRole(Role.valueOf(rs.getString("role")));
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public int getTotalUsersCount() throws SQLException {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM users");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}