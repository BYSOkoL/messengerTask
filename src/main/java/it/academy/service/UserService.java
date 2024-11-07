package it.academy.service;

import it.academy.api.service.IUserService;
import it.academy.api.storage.IUserStorage;
import it.academy.dto.LoginDto;
import it.academy.dto.UserDto;
import it.academy.entity.User;
import it.academy.storage.UserStorage;
import it.academy.validation.LoginValidator;
import it.academy.validation.UserValidator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class UserService implements IUserService {

    private final IUserStorage userStorage;

    public UserService(IUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void registerUser(UserDto userDto) throws SQLException {
        if (UserValidator.isValid(userDto)) {
            User user = User.builder()
                    .login(userDto.getLogin())
                    .password(userDto.getPassword())
                    .fullName(userDto.getFullName())
                    .birthDate(userDto.getBirthDate())
                    .registrationDate(LocalDate.now())
                    .role(User.Role.USER)
                    .build();
            userStorage.save(user);
        } else {
            throw new IllegalArgumentException("Invalid user data");
        }
    }

    @Override
    public Optional<User> loginUser(LoginDto loginDto) throws SQLException {
        if (LoginValidator.isValid(loginDto)) {
            return userStorage.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
        } else {
            throw new IllegalArgumentException("Invalid login data");
        }
    }

    @Override
    public int getTotalUsersCount() throws SQLException {
        return userStorage.getTotalUsersCount();
    }
}
