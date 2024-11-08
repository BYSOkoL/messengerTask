package it.academy.validation;

import it.academy.dto.LoginDto;

public class LoginValidator {
    public static void validate(LoginDto loginDto) {
        if (loginDto.getLogin() == null || loginDto.getLogin().isEmpty()) {
            throw new IllegalArgumentException("Login cannot be empty");
        }
        if (loginDto.getPassword() == null || loginDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }
}