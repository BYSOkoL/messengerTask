package it.academy.service;

import it.academy.api.service.IUserService;
import it.academy.dto.LoginDto;
import it.academy.dto.UserDto;
import it.academy.entity.User;
import it.academy.mapper.UserMapper;
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
        // Валидация данных пользователя
        UserValidator.validate(userDto);

        User user = UserMapper.toEntity(userDto); // Использование маппера для преобразования DTO в сущность
        userStorage.save(user);
    }

    @Override
    public Optional<User> loginUser(LoginDto loginDto) throws SQLException {
        // Валидация данных для входа
        LoginValidator.validate(loginDto);

        return userStorage.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
    }

    @Override
    public int getTotalUsersCount() throws SQLException {
        return userStorage.getTotalUsersCount();
    }
}