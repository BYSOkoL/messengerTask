package it.academy.api.storage;

import it.academy.entity.User;
import java.sql.SQLException;
import java.util.Optional;

public interface IUserStorage {
    void save(User user) throws SQLException;
    Optional<User> findByRole(User.Role role) throws SQLException;
    Optional<User> findByLoginAndPassword(String login, String password) throws SQLException;
    int getTotalUsersCount() throws SQLException;
}
