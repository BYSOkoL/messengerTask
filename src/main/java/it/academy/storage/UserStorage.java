package it.academy.storage;

import it.academy.api.storage.IUserStorage;
import it.academy.entity.User;
import it.academy.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserStorage implements IUserStorage {

    @Override
    public synchronized void save(User user) throws SQLException {
        String sql = "INSERT INTO users (login, password, full_name, birth_date, registration_date, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setDate(4, java.sql.Date.valueOf(user.getBirthDate()));
            statement.setDate(5, java.sql.Date.valueOf(user.getRegistrationDate()));
            statement.setString(6, user.getRole().name());

            statement.executeUpdate();
        }
    }

    @Override
    public Optional<User> findByRole(User.Role role) throws SQLException {
        String sql = "SELECT * FROM users WHERE role = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, role.name());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = User.builder()
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .fullName(resultSet.getString("full_name"))
                        .birthDate(resultSet.getDate("birth_date").toLocalDate())
                        .registrationDate(resultSet.getDate("registration_date").toLocalDate())
                        .role(User.Role.valueOf(resultSet.getString("role")))
                        .build();
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE login = ? AND password = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = User.builder()
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .fullName(resultSet.getString("full_name"))
                        .birthDate(resultSet.getDate("birth_date").toLocalDate())
                        .registrationDate(resultSet.getDate("registration_date").toLocalDate())
                        .role(User.Role.valueOf(resultSet.getString("role")))
                        .build();
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public int getTotalUsersCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM users";
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
