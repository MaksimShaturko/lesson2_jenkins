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

public class GoToAddUserPage implements Command {
    private final static GoToAddUserPage INSTANCE = new GoToAddUserPage();
    public static GoToAddUserPage getInstance() {
        return INSTANCE;
    }

    private GoToAddUserPage() {
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.REQUEST_URL, Constant.URL_TO_ADD_USER_PAGE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagePath.ADD_USER);
        requestDispatcher.forward(request, response);
    }
}
