package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;
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

public class LoginCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final Validator validator = ValidatorImpl.getInstance();

    private final String USER_LOGIN = "userloginLog";
    private final String PASSWORD = "passwordLog";
    private final String ID = "id";
    private final String ROLE = "role";
    private final String LOGIN = "login";
    private final String LOCALE = "locale";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to LoginCommand");

        String login = req.getParameter(USER_LOGIN);
        String password = req.getParameter(PASSWORD);
        String locale = req.getParameter(LOCALE);
        System.out.println(locale);
        List<String> params = new ArrayList<>();
        params.add(login);
        params.add(password);
        try {
            if (validator.validateIsEmpty(params)) {
                LOGGER.info("Empty fields");
                res.sendRedirect("index.jsp?message=empty");
            } else {
                password = DigestUtils.md5Hex(password);
                User user = new User(login, password);
                UserDTO userDTO = userService.logination(user);
                if (validator.validateIsNull(userDTO)) {
                    LOGGER.info("There is no such user (Error)");
                    res.sendRedirect("index.jsp?message=noSuchUser");
                } else {
                    req.getSession(true).setAttribute(ID, userDTO.getId());
                    req.getSession().setAttribute(ROLE, userDTO.getRole());
                    req.getSession().setAttribute(LOGIN, userDTO.getLogin());
                    req.getSession().setAttribute(LOCALE, locale);
                    if (userDTO.getRole() == 1 || userDTO.getRole() == 3) {
                        LOGGER.info("Going to admin page");
                        req.getRequestDispatcher("adminPage.jsp").forward(req, res);
                    }else {
                        LOGGER.info("Going to user page");
                        res.sendRedirect("userPage.jsp");
                    }
                }
            }
        } catch (IOException | ServiceException | ServletException e) {
            LOGGER.info("Service exception");
            throw new ControllerException(e);
        }
    }

}
