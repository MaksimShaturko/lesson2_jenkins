package main.command.impl;

import main.command.Command;
import main.command.Constant;
import main.command.SessionAttribute;
import main.entity.User;
import main.service.GetAllUsersService;
import main.service.ServiceProvider;
import main.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetAllUsersCommand implements Command {
    private final static GetAllUsersCommand INSTANCE = new GetAllUsersCommand();
    public static GetAllUsersCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceProvider provider = ServiceProvider.getInstance();
        GetAllUsersService gettingData = provider.getGetAllUserService();
        HttpSession session = request.getSession(true);
        String requestUrl = Constant.URL_TO_USERS_PAGE;
        try {
            List<User> listOfUsers = gettingData.getAllUsers();
            session.setAttribute(SessionAttribute.LIST_OF_USERS, listOfUsers);
        } catch (ServiceException e) {
            session.setAttribute(SessionAttribute.ERROR_TYPE, Constant.ERROR_500);
            session.setAttribute(SessionAttribute.ERROR_MESSAGE, e);
            requestUrl = Constant.ERROR_COMMAND;
        }
        response.sendRedirect(requestUrl);
    }
}
