package main.command.impl;

import main.command.Command;
import main.command.Constant;
import main.command.SessionAttribute;
import main.entity.User;
import main.service.GetAllUsersService;
import main.service.GetUserService;
import main.service.ServiceProvider;
import main.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetUserCommand implements Command {
    private final static GetUserCommand INSTANCE = new GetUserCommand();

    public static GetUserCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceProvider provider = ServiceProvider.getInstance();
        HttpSession session = request.getSession(true);
        String requestUrl = Constant.URL_TO_EDIT_USER_PAGE;
        int userId = Integer.parseInt(request.getParameter("user_id"));

        try {
            User user = provider.getGetUserService().getUser(userId);
            session.setAttribute(SessionAttribute.USER, user);
        } catch (ServiceException e) {
            session.setAttribute(SessionAttribute.ERROR_TYPE, Constant.ERROR_500);
            session.setAttribute(SessionAttribute.ERROR_MESSAGE, e);
            requestUrl = Constant.ERROR_COMMAND;
        }
        response.sendRedirect(requestUrl);
    }
}
