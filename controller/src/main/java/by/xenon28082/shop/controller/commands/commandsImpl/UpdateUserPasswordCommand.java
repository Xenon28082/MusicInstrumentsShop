package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UpdateUserPasswordCommand implements Command {

    private final Logger LOGGER = LoggerFactory.getLogger(UpdateUserPasswordCommand.class);

    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final Validator validator = ValidatorImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to UpdateUserPasswordCommand");;
        String newPassword = req.getParameter("newPassword");
        String repeatNewPassword = req.getParameter("repeatNewPassword");
        String userId = req.getParameter("userId");
        String userLogin = (String) req.getSession().getAttribute("login");
        try {
            List<String> params = Arrays.asList(newPassword, repeatNewPassword, userId);
            if (validator.validateIsEmpty(params)) {
                LOGGER.info("Empty params");
                res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + userLogin + "&message=empty");
            } else {
                if (!newPassword.equals(repeatNewPassword)) {
                    LOGGER.info("Repeated password doesn't match");
                    res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + userLogin + "&message=notEqual");
                } else {
                    newPassword = DigestUtils.md5Hex(newPassword);
                    if (userService.updateUserPassword(newPassword, Long.parseLong(userId))) {
                        LOGGER.info("Success update");
                        res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + userLogin + "&message=success");
                    } else {
                        LOGGER.info("Update failed");
                        res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + userLogin + "&message=failed");
                    }
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }

    }
}
