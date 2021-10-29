package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String login = request.getParameter("userloginLog");
        String password = request.getParameter("passwordLog");
        User user = new User(login, password);
        UserDTO userDTO = userService.logination(user);
        if(userDTO == null){
            System.out.println("Error");
            request.getSession(true).setAttribute("test", "This is test session");
            response.sendRedirect("index.jsp?message=noSuchUser");
        }
        else{
            request.getSession(true).setAttribute("id", userDTO.id);
            request.getSession().setAttribute("role",  userDTO.role);
            request.getSession().setAttribute("login",  userDTO.login);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        }
    }

    @Override
    public void print() {
        System.out.println("\"Login\" command");
    }
}
