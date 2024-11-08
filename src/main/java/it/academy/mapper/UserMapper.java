
package it.academy.mapper;

import it.academy.dto.UserDto;
import it.academy.entity.Role;
import it.academy.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setFullName(user.getFullName());
        dto.setBirthDate(user.getBirthDate());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullName());
        user.setBirthDate(dto.getBirthDate());
        user.setRole(Role.USER);
        return user;
    }
}
