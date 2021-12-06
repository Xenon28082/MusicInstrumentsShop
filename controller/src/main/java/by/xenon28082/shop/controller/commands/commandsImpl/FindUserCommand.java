package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FindUserCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindUserCommand.class);

    private UserService userService = ServiceFactory.getInstance().getUserService();
    private final Validator validator = ValidatorImpl.getInstance();

    private static final String USER_LOGIN = "userLogin";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to FindUserCommand");
        String role = String.valueOf(req.getSession().getAttribute("role"));
        String path = "";

        if (role.equals("2")) {
            path = "updateUser.jsp";
        } else {
            path = "changeUserPage.jsp";
        }
        try {
            String userLogin = req.getParameter(USER_LOGIN);
            if (userLogin == "") {
                LOGGER.info("All fields must be fulfilled (ERROR)");
                res.sendRedirect(path + "?message=fieldsMustBeFulfilled");
            } else {
                User foundUser = userService.findUserByLogin(userLogin);
                if (validator.validateIsNull(foundUser)) {
                    LOGGER.info("No user");
                    res.sendRedirect(path + "?message=noUser");
                } else {
                    if (foundUser.getRole() != 3) {
                        req.setAttribute("foundUser", foundUser);
                    } else {
                        res.sendRedirect(path + "?message=Cant change dir");
                    }
                    req.getRequestDispatcher(path).forward(req, res);
                }
            }

        } catch (ServletException | ServiceException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

