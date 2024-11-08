package it.academy.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDto {
    private String login;
    private String password;
    private String fullName;
    private LocalDate birthDate;
}