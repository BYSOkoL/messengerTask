package it.academy.service;

import it.academy.api.service.IUserService;
import it.academy.dto.LoginDto;
import it.academy.dto.UserDto;
import it.academy.entity.Role;
import it.academy.entity.User;
import it.academy.storage.UserStorage;
import it.academy.validation.LoginValidator;
import it.academy.validation.UserValidator;

import java.sql.SQLException;
import java.util.Optional;

public class UserService implements IUserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void registerUser(UserDto userDto) throws SQLException {
        UserValidator.validate(userDto);

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());
        user.setBirthDate(userDto.getBirthDate());
        user.setRole(Role.USER);
        userStorage.save(user);
    }

    @Override
    public Optional<User> loginUser(LoginDto loginDto) throws SQLException {
        LoginValidator.validate(loginDto);

        return userStorage.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
    }

    @Override
    public int getTotalUsersCount() throws SQLException {
        return userStorage.getTotalUsersCount();
    }
}