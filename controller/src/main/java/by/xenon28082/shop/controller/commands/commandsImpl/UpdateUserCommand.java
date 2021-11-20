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

public class UpdateUserCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(UpdateUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        logger.info("Got to UpdateUserCommand");
        String userLogin = req.getParameter("userLogin");
        String userName = req.getParameter("userFirstname");
        String userLastname = req.getParameter("userLastname");
        int userRole = Integer.parseInt(req.getParameter("userRole"));
        long id = Long.parseLong(req.getParameter("userId"));
        int lastRole = Integer.parseInt(req.getParameter("userLastRole"));
        int currentRole = (int) req.getSession().getAttribute("role");
        long currentId = (long) req.getSession().getAttribute("id");

        if (userLogin == "" ||
                userName == "" ||
                userLastname == "" ||
                userRole == 0 ||
                id == 0
        ) {
            logger.info("all fields must be fulfilled (ERROR)");
            req.getRequestDispatcher("changeUserPage.jsp?message=fieldsMustBeFulfilled").forward(req, res);
        }
        if(lastRole == 1 && userRole == 2 && currentRole != 3){
            logger.info("Trying to delete admin being not a director (ERROR)");
            req.getRequestDispatcher("changeUserPage.jsp?message=mustBeADir").forward(req, res);
        }
        if(currentId == id){
            logger.info("Trying to update himself");
            req.getRequestDispatcher("changeUserPage.jsp?message=updateSelf").forward(req, res);
        }
        UserService userService = new UserServiceImpl();
        User user = new User(
                userLogin,
                userName,
                userLastname,
                id,
                userRole
        );
        boolean isUpdated = userService.updateUserRole(user);
        if(isUpdated){
            logger.info("Update:" + user + "(SUCCESS)");
        }else{
            logger.info("Update:" + user + "(FAILED)");
        }
        req.getRequestDispatcher("changeUserPage.jsp").forward(req, res);
    }
}
