package it.academy.controller.servlet;

import it.academy.api.service.IUserService;
import it.academy.dto.LoginDto;
import it.academy.entity.User;
import it.academy.service.UserService;
import it.academy.storage.UserStorage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserStorage());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        LoginDto loginDto = new LoginDto();
        loginDto.setLogin(login);
        loginDto.setPassword(password);

        try {
            Optional<User> user = userService.loginUser(loginDto);
            if (user.isPresent()) {
                request.getSession().setAttribute("user", user.get());
                response.sendRedirect("index.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid login credentials");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to login user");
        }
    }
}