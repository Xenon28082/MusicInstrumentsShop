package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CountUsersCommand implements Command {
    private UserService userService = new UserServiceImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            long count = userService.countUsers();
            System.out.println("count - " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void print() {
        System.out.println("\"Count Users\" command");
    }
}
