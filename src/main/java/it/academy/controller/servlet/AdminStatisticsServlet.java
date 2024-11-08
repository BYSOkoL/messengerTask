package it.academy.controller.servlet;

import it.academy.api.service.IUserService;
import it.academy.dto.StatisticsDto;
import it.academy.service.UserService;
import it.academy.storage.UserStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/statistics")
public class AdminStatisticsServlet extends HttpServlet {
    private IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserStorage());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int totalUsers = userService.getTotalUsersCount();
            StatisticsDto statisticsDto = new StatisticsDto();
            statisticsDto.setTotalUsers(totalUsers);

            request.setAttribute("statistics", statisticsDto);
            request.getRequestDispatcher("/WEB-INF/views/admin/statistics.jsp").forward(request, response);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to fetch statistics");
        }
    }
}