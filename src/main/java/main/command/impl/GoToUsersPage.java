package main.command.impl;

import main.command.Command;
import main.command.Constant;
import main.command.PagePath;
import main.command.SessionAttribute;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToUsersPage implements Command {
    private final static GoToUsersPage INSTANCE = new GoToUsersPage();
    public static GoToUsersPage getInstance() {
        return INSTANCE;
    }

    private GoToUsersPage() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.REQUEST_URL, Constant.URL_TO_USERS_PAGE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagePath.USERS);
        requestDispatcher.forward(request, response);
    }


}
