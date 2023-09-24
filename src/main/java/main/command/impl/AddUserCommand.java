package main.command.impl;

import main.command.Command;
import main.command.Constant;
import main.command.SessionAttribute;
import main.controller.RequestParameter;
import main.entity.User;
import main.service.AddUserService;
import main.service.GetAllUsersService;
import main.service.ServiceProvider;
import main.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddUserCommand implements Command {
    private final static AddUserCommand INSTANCE = new AddUserCommand();

    public static AddUserCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceProvider provider = ServiceProvider.getInstance();
        AddUserService addUserService = provider.getAddUserService();
        HttpSession session = request.getSession(true);
        String requestUrl = Constant.GET_ALL_USERS;

        System.out.println("HERE!");
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        int age = Integer.parseInt(request.getParameter(RequestParameter.AGE));
        String email = request.getParameter(RequestParameter.EMAIL);
        User user = new User(name, surname, age, email);

        try {
            addUserService.addUser(user);
        } catch (ServiceException e) {
            session.setAttribute(SessionAttribute.ERROR_TYPE, Constant.ERROR_500);
            session.setAttribute(SessionAttribute.ERROR_MESSAGE, e);
            requestUrl = Constant.ERROR_COMMAND;
        }
        response.sendRedirect(requestUrl);
    }
}

