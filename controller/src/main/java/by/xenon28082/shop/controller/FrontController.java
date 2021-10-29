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
        System.out.println("DOGET");
        try {
            execute(req, resp);
        } catch (SQLException | ServletException throwables) {
            throwables.printStackTrace();
        }
//        String login = req.getParameter("userloginLog");
//        String password = req.getParameter("passwordLog");
//
//        try {
//            if(PostgresImpl.validateUser(login, password)){
//                req.getRequestDispatcher("userPage.jsp").forward(req, resp);
//            }
//            else{
//                req.getRequestDispatcher("loginFailed.jsp").forward(req, resp);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DOPOST");
        try {
            execute(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        req.getRequestDispatcher("try.jsp").forward(req, resp);
//        String option = req.getParameter("option");

//        String login = req.getParameter("userlogin");
//        String name = req.getParameter("username");
//        String lastname = req.getParameter("userlastname");
//        String password = req.getParameter("password");
//
//        req.setAttribute("login", login);
//        req.setAttribute("name", name);
//        req.setAttribute("lastname", lastname);
//        req.setAttribute("password", password);
//        User user = new User(login, name, lastname, password);
//        try {
//            Connection connection = DataBaseConfig.getConnection();
//            if(connection != null){
//                System.out.println("OK");
//            }
//            else {
//                System.out.println("NOT OK");
//            }
//            PostgresImpl.addUser(user);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        req.getRequestDispatcher("userPage.jsp").forward(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String choice = req.getParameter("COMMAND");
        Command command = CommandsMap.getCommand(choice);
        command.execute(req, resp);
    }
}
