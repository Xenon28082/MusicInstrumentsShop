package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FindUserCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(FindUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        logger.info("Got to FindUserCommand");
        String userLogin = req.getParameter("userLogin");
        if(userLogin == ""){
            logger.info("All fields must be fulfilled (ERROR)");
            req.getRequestDispatcher("changeUserPage.jsp?message=fieldsMustBeFulfilled").forward(req, res);
        }
            UserService userService = new UserServiceImpl();
            User foundUser = userService.findUserByLogin(userLogin);
            if (foundUser.getRole() != 3) {
                req.setAttribute("foundUser", foundUser);
            } else {

                System.out.println("This is a director");
            }
            req.getRequestDispatcher("changeUserPage.jsp").forward(req, res);
    }
}
