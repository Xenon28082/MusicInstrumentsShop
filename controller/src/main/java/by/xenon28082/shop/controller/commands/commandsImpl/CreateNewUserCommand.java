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
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateNewUserCommand implements Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private static final Logger LOGGER = LoggerFactory.getLogger(AddToBasketCommand.class);

    private final Validator validator = ValidatorImpl.getInstance();

    private static final String USER_LOGIN = "userlogin";
    private static final String USER_NAME = "username";
    private static final String USER_LASTNAME = "userlastname";
    private static final String PASSWORD = "password";
    private static final String CHECK_PASSWORD = "checkPassword";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to CreateNewUserCommand");
        String login = req.getParameter(USER_LOGIN);
        String name = req.getParameter(USER_NAME);
        String lastname = req.getParameter(USER_LASTNAME);
        String password = req.getParameter(PASSWORD);
        String checkPassword = req.getParameter(CHECK_PASSWORD);

        List<String> params = new ArrayList<>();
        params.add(login);
        params.add(name);
        params.add(lastname);
        params.add(password);
        params.add(checkPassword);
        try {
            if (validator.validateIsEmpty(params)) {
                LOGGER.info("Empty fields");

                res.sendRedirect("index.jsp?message=empty");

            } else {
                if (!password.equals(checkPassword)) {
                    LOGGER.info("Passwords don't match(Error)");
                    res.sendRedirect("index.jsp?message=passwordsnotmatch");
                } else {
                    password = DigestUtils.md5Hex(password);
                    User newUser = new User(login, name, lastname, password);
                    User user = null;
                    try {
                        user = userService.registration(newUser);
                    } catch (ServiceException throwables) {
                        res.sendRedirect("index.jsp?message=loginExists");
                    }
                    if (user != null) {
                        req.getSession(true).setAttribute("login", user.getLogin());
                        req.getSession().setAttribute("id", user.getId());
                        req.getSession().setAttribute("role", user.getRole());
                        LOGGER.info("Created new User:login - " + login + " (SUCCESS)");
                        req.getRequestDispatcher("userPage.jsp").forward(req, res);
                    } else {
                        LOGGER.info("Failed to create new user (FAILED)");
                        res.sendRedirect("index.jsp?message=failedToRegister");
                    }
                }
            }
        } catch (IOException | ServletException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }

}
