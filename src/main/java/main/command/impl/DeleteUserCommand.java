package main.command.impl;

import main.command.Command;
import main.command.Constant;
import main.command.SessionAttribute;
import main.entity.User;
import main.service.DeleteUserService;
import main.service.GetAllUsersService;
import main.service.ServiceProvider;
import main.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteUserCommand implements Command {
    private final static DeleteUserCommand INSTANCE = new DeleteUserCommand();

    public static DeleteUserCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceProvider provider = ServiceProvider.getInstance();
        DeleteUserService deleteUserService = provider.getDeleteUserService();
        HttpSession session = request.getSession(true);
        String requestUrl = Constant.GET_ALL_USERS;
        try {
            int userId = Integer.parseInt(request.getParameter("user_id"));
            deleteUserService.deleteUser(userId);
        } catch (ServiceException e) {
            session.setAttribute(SessionAttribute.ERROR_TYPE, Constant.ERROR_500);
            session.setAttribute(SessionAttribute.ERROR_MESSAGE, e);
            requestUrl = Constant.ERROR_COMMAND;
        }
        response.sendRedirect(requestUrl);
    }
}

