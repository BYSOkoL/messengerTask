package it.academy.factory;

import it.academy.dto.UserDto;
import it.academy.entity.Role;
import it.academy.entity.User;

public class UserFactory {
    public static User createUser(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());
        user.setBirthDate(userDto.getBirthDate());
        user.setRole(Role.USER);
        return user;
    }
}