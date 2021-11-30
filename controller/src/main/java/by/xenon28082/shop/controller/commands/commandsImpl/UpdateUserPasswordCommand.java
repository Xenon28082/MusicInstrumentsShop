package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UpdateUserPasswordCommand implements Command {

    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final Validator validator = ValidatorImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException, DaoException {
        String newPassword = req.getParameter("newPassword");
        String repeatNewPassword = req.getParameter("repeatNewPassword");
        String userId = req.getParameter("userId");
        String userLogin = (String) req.getSession().getAttribute("login");

        List<String> params = Arrays.asList(newPassword, repeatNewPassword, userId);
        if (validator.validateIsEmpty(params)) {
            res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + userLogin + "&message=empty");
        } else {
            if (!newPassword.equals(repeatNewPassword)) {
                res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + userLogin + "&message=notEqual");
            } else {
                newPassword = DigestUtils.md5Hex(newPassword);
                boolean isUpdated = userService.updateUserPassword(newPassword, Long.parseLong(userId));
                if (isUpdated) {
                    res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + userLogin + "&message=success");
                }
                res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + userLogin + "&message=success");
            }
        }

    }
}
