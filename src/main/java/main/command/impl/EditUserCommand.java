package main.command.impl;

import main.command.Command;
import main.command.Constant;
import main.command.SessionAttribute;
import main.controller.RequestParameter;
import main.entity.User;
import main.service.DeleteUserService;
import main.service.EditUserService;
import main.service.ServiceProvider;
import main.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditUserCommand implements Command {
    private final static EditUserCommand INSTANCE = new EditUserCommand();

    public static EditUserCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceProvider provider = ServiceProvider.getInstance();
        EditUserService editUserService = provider.getEditUserService();
        HttpSession session = request.getSession(true);
        String requestUrl = Constant.GET_ALL_USERS;
        try {
            int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
            String name = request.getParameter(RequestParameter.NAME);
            String surname = request.getParameter(RequestParameter.SURNAME);
            int age = Integer.parseInt(request.getParameter(RequestParameter.AGE));
            String email = request.getParameter(RequestParameter.EMAIL);
            User user = new User(id, name, surname, age, email);
            editUserService.editUser(user);
        } catch (ServiceException e) {
            session.setAttribute(SessionAttribute.ERROR_TYPE, Constant.ERROR_500);
            session.setAttribute(SessionAttribute.ERROR_MESSAGE, e);
            requestUrl = Constant.ERROR_COMMAND;
        }
        response.sendRedirect(requestUrl);
    }
}
