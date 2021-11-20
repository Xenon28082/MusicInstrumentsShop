package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        logger.info("Got to LoginCommand");
        UserService userService = new UserServiceImpl();
        String login = request.getParameter("userloginLog");
        String password = request.getParameter("passwordLog");
        User user = new User(login, password);
        UserDTO userDTO = userService.logination(user);
        if(userDTO == null){
            logger.info("There is no such user (Error)");
            request.getSession(true).setAttribute("test", "This is test session");
            response.sendRedirect("index.jsp?message=noSuchUser");
        }
        else{
            request.getSession(true).setAttribute("id", userDTO.getId());
            request.getSession().setAttribute("role",  userDTO.getRole());
            request.getSession().setAttribute("login",  userDTO.getLogin());

            if(userDTO.getRole() == 1 || userDTO.getRole() == 3){
                logger.info("Going to admin page");
                request.getRequestDispatcher("adminPage.jsp").forward(request, response);
                return;
            }
            logger.info("Going to user page");
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        }
    }

}
