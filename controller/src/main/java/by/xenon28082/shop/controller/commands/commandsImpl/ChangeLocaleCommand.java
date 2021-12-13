package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(ChangeLocaleCommand.class);

    private final String LOCALE = "locale";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to ChangeLocaleCommand");
        try {
            String newLocale = req.getParameter("localeSelect");
            String returnValue = req.getParameter("pathBack");
            LOGGER.info("New language is - " + newLocale);
            req.getSession().setAttribute("loc", newLocale);
            res.sendRedirect(returnValue+"");
        } catch (IOException e) {
            throw new ControllerException(e);
        }
    }
}
