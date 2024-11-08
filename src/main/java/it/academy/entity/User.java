package it.academy.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class User {
    private String login;
    private String password;
    private String fullName;
    private LocalDate birthDate;
    private Role role;
}