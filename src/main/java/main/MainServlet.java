package main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        writer.println("Hello World! It works automatically! \nSecond attempt " +
                "jkjii grgeg fgdgfg dbdhdg" );
    }

}
