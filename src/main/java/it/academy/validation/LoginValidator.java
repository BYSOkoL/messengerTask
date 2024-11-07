package it.academy.validation;

import it.academy.dto.LoginDto;

public class LoginValidator {

    public static boolean isValid(LoginDto loginDto) {
        return isValidLogin(loginDto.getLogin()) && isValidPassword(loginDto.getPassword());
    }

    private static boolean isValidLogin(String login) {
        return login != null && !login.trim().isEmpty();
    }

    private static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}