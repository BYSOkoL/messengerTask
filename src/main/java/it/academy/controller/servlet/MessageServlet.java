package it.academy.controller.servlet;

import it.academy.api.service.IMessageService;
import it.academy.dto.MessageDto;
import it.academy.entity.Message;
import it.academy.service.MessageService;
import it.academy.storage.MessageStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/messages")
public class MessageServlet extends HttpServlet {
    private IMessageService messageService;

    @Override
    public void init() throws ServletException {
        messageService = new MessageService(new MessageStorage());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromUser = request.getParameter("fromUser");
        String toUser = request.getParameter("toUser");
        String text = request.getParameter("text");

        MessageDto messageDto = new MessageDto();
        messageDto.setFromUser(fromUser);
        messageDto.setToUser(toUser);
        messageDto.setText(text);

        try {
            messageService.sendMessage(messageDto);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to send message");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");

        try {
            List<Message> messages = messageService.getMessagesByUser(user);
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/WEB-INF/views/messages.jsp").forward(request, response);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to fetch messages");
        }
    }
}