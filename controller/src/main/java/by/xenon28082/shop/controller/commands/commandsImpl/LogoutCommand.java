package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {

    private final Logger LOGGER = LoggerFactory.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        try {
            LOGGER.info("Got to logout");
            req.getSession().invalidate();
            res.sendRedirect("index.jsp");
        } catch (IOException e) {
            LOGGER.info("IOException");
            throw new ControllerException(e);
        }
    }

}
