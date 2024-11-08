package it.academy.validation;

import it.academy.dto.UserDto;

import java.time.LocalDate;

public class UserValidator {
    public static void validate(UserDto userDto) {
        if (userDto.getLogin() == null || userDto.getLogin().isEmpty()) {
            throw new IllegalArgumentException("Login cannot be empty");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (userDto.getFullName() == null || userDto.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        if (userDto.getBirthDate() == null || userDto.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date must be in the past");
        }
    }
}