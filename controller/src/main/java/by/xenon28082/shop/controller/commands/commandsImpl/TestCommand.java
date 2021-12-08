package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        System.out.println("Came to test command");
    }
}
