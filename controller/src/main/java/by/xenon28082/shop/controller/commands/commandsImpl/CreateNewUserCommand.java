package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateNewUserCommand implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String login = req.getParameter("userlogin");
        String name = req.getParameter("username");
        String lastname = req.getParameter("userlastname");
        String password = req.getParameter("password");
        User newUser = new User(login, name, lastname, password);
        User user = null;
        try {
            user = userService.registration(newUser);
        } catch (SQLException throwables) {
            res.sendRedirect("index.jsp?message=loginExists");
        }
        if(user != null){
            req.getSession(true).setAttribute("login", user.getLogin());
            req.getSession().setAttribute("id", user.getId());
            req.getSession().setAttribute("role", user.getRole());
            System.out.println("Successfull registration");
            req.getRequestDispatcher("userPage.jsp").forward(req, res);
        }
        else{
            System.out.println("Reg failed");
        }
    }

    public void print() {
        System.out.println("You came in right execute block");
    }


    @Override
    public String toString() {
        return "\"Create new user\" command";
    }
}
