package by.xenon28082.shop.controller;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.commands.CommandsMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class FrontController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            execute(req, resp);
        } catch (SQLException | ServletException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            execute(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String choice = req.getParameter("COMMAND");
        Command command = CommandsMap.getCommand(choice);
        command.execute(req, resp);
    }
}
