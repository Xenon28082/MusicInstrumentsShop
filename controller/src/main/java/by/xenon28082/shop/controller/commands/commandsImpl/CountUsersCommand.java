package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CountUsersCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(AddToBasketCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Got to CountUsersCommand");
        try {
            long count = userService.countUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
