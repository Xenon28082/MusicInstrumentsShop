package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class UpdateUserLoginCommand implements Command {

    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final Validator validator = ValidatorImpl.getInstance();

    private static final String LOGIN = "newLogin";
    private static final String ID = "userId";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException, DaoException {
        String newLogin = req.getParameter(LOGIN);
        String userId = req.getParameter(ID);

        List<String> params = Arrays.asList(newLogin, userId);
        if(validator.validateIsEmpty(params)){
            res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + newLogin + "$message=empty");
        }else{
            boolean isUpdated = userService.updateUserLogin(newLogin, Long.parseLong(userId));
            if(isUpdated){
                res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + newLogin + "&message=success");
            }
            res.sendRedirect("FrontController?COMMAND=GET_USER_INFO&userLogin=" + newLogin + "$message=error");
        }

    }
}
