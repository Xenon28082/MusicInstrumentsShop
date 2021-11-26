package by.xenon28082.shop.controller;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.commands.CommandsMap;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.exception.ServiceException;

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
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            execute(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException, ServiceException, DaoException {
        String choice = req.getParameter("COMMAND");
        CommandsMap map = new CommandsMap();
        Command command = map.getCommand(choice);
        command.execute(req, resp);
    }
}
