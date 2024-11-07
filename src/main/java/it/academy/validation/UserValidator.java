package it.academy.validation;

import it.academy.dto.UserDto;

import java.time.LocalDate;

public class UserValidator {

    public static boolean isValid(UserDto userDto) {
        return isValidLogin(userDto.getLogin()) && isValidPassword(userDto.getPassword())
                && isValidFullName(userDto.getFullName()) && isValidBirthDate(userDto.getBirthDate());
    }

    private static boolean isValidLogin(String login) {
        return login != null && !login.trim().isEmpty();
    }

    private static boolean isValidPassword(String password) {
        return password != null;
    }

    private static boolean isValidFullName(String fullName) {
        return fullName != null && !fullName.trim().isEmpty();
    }

    private static boolean isValidBirthDate(LocalDate birthDate) {
        return birthDate != null && birthDate.isBefore(LocalDate.now());
    }
}