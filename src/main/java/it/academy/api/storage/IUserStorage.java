package it.academy.api.storage;

import it.academy.entity.Role;
import it.academy.entity.User;
import java.sql.SQLException;
import java.util.Optional;

// Интерфейс для работы с хранилищем пользователей
public interface IUserStorage {
    void save(User user) throws SQLException;
    Optional<User> findByRole(Role role) throws SQLException;
    Optional<User> findByLoginAndPassword(String login, String password) throws SQLException;
    int getTotalUsersCount() throws SQLException;
}