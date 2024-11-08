package it.academy.controller.servlet;

import it.academy.api.service.IUserService;
import it.academy.dto.UserDto;
import it.academy.service.UserService;
import it.academy.storage.UserStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserStorage());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String birthDate = request.getParameter("birthDate");

        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setFullName(fullName);
        userDto.setBirthDate(LocalDate.parse(birthDate));

        try {
            userService.registerUser(userDto);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to register user");
        }
    }
}