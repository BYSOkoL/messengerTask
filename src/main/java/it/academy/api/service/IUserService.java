package it.academy.api.service;

import it.academy.dto.LoginDto;
import it.academy.dto.UserDto;
import it.academy.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface IUserService {
    void registerUser(UserDto userDto) throws SQLException;
    Optional<User> loginUser(LoginDto loginDto) throws SQLException;
    int getTotalUsersCount() throws SQLException;
}
