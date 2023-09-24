package main.command.impl;

import main.command.Command;
import main.command.Constant;
import main.command.PagePath;
import main.command.SessionAttribute;
import main.entity.User;
import main.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToEditUserPage implements Command {
    private final static GoToEditUserPage INSTANCE = new GoToEditUserPage();
    public static GoToEditUserPage getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.REQUEST_URL, Constant.URL_TO_EDIT_USER_PAGE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagePath.EDIT_USER);
        requestDispatcher.forward(request, response);
    }
}
