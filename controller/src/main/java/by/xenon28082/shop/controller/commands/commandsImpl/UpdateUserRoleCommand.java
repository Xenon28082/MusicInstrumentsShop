package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.entity.User;
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

public class UpdateUserRoleCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(UpdateUserRoleCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private final String USER_LOGIN = "userLogin";
    private final String USER_FIRSTNAME = "userFirstname";
    private final String USER_LASTNAME = "userLastname";
    private final String USER_ROLE = "userRole";
    private final String USER_ID = "userId";
    private final String USER_LASTROLE = "userLastRole";
    private final String ROLE = "role";
    private final String ID = "id";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to UpdateUserCommand");
        String userLogin = req.getParameter(USER_LOGIN);
        String userName = req.getParameter(USER_FIRSTNAME);
        String userLastname = req.getParameter(USER_LASTNAME);
        int userRole = Integer.parseInt(req.getParameter(USER_ROLE));
        long id = Long.parseLong(req.getParameter(USER_ID));
        int lastRole = Integer.parseInt(req.getParameter(USER_LASTROLE));
        int currentRole = (int) req.getSession().getAttribute(ROLE);
        long currentId = (long) req.getSession().getAttribute(ID);

        try {
            if (userLogin == "" ||
                    userName == "" ||
                    userLastname == "" ||
                    userRole == 0 ||
                    id == 0
            ) {
                LOGGER.info("all fields must be fulfilled (ERROR)");
                req.getRequestDispatcher("changeUserPage.jsp?message=fieldsMustBeFulfilled").forward(req, res);
            } else {
                if (lastRole == 1 && userRole == 2 && currentRole != 3) {
                    LOGGER.info("Trying to delete admin being not a director (ERROR)");
                    req.getRequestDispatcher("changeUserPage.jsp?message=mustBeADir").forward(req, res);
                } else {
                    if (currentId == id) {
                        LOGGER.info("Trying to update himself");
                        req.getRequestDispatcher("changeUserPage.jsp?message=updateSelf").forward(req, res);
                    } else {
                        User user = new User(
                                userLogin,
                                userName,
                                userLastname,
                                id,
                                userRole
                        );
                        if (userService.updateUserRole(user)) {
                            LOGGER.info("Update:" + user + "(SUCCESS)");
                            req.getRequestDispatcher("changeUserPage.jsp?message=updated").forward(req, res);
                        } else {
                            LOGGER.info("Update:" + user + "(FAILED)");
                            req.getRequestDispatcher("changeUserPage.jsp?message=failed").forward(req, res);
                        }
                    }
                }
            }
        } catch (ServletException | IOException | ServiceException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }
}
