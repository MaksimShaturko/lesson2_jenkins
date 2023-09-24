package main.controller;

import main.command.Command;
import main.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

//@WebServlet("/main")
public class Controller extends HttpServlet {

    private final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        Optional<Command> commandOptional = commandProvider.getCommand(commandName);
        Command command = commandOptional.orElseThrow(IllegalArgumentException::new);
        command.execute(request, response);
    }


}
