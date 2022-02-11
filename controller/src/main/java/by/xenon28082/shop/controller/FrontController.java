package by.xenon28082.shop.controller;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.commands.CommandsMap;
import by.xenon28082.shop.controller.exception.ControllerException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String choice = req.getParameter("COMMAND");
        CommandsMap map = new CommandsMap();
        Command command = map.getCommand(choice);
        try {
            command.execute(req, resp);
        } catch (ControllerException e) {
            resp.sendRedirect("FrontController?COMMAND=LOGOUT&message=fatal");
        }
    }
}
