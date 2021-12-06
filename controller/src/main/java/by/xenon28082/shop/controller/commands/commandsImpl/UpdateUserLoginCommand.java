package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class UpdateUserLoginCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(UpdateUserLoginCommand.class);

    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final Validator validator = ValidatorImpl.getInstance();

    private static final String LOGIN = "newLogin";
    private static final String ID = "userId";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to UpdateUserLogin Command");
        String newLogin = req.getParameter(LOGIN);
        String userId = req.getParameter(ID);
        try {
            List<String> params = Arrays.asList(newLogin, userId);
            if (validator.validateIsEmpty(params)) {
                LOGGER.info("Empty params");
                res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + newLogin + "$message=empty");
            } else {
                boolean isUpdated = userService.updateUserLogin(newLogin, Long.parseLong(userId));
                if (isUpdated) {
                    LOGGER.info("Success update");
                    req.getSession().setAttribute("login", newLogin);
                    res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + newLogin + "&message=success");
                } else {
                    LOGGER.info("Update failed");
                    res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + newLogin + "$message=error");
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }
}
