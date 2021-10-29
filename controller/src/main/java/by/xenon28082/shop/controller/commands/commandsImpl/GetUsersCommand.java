package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUsersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Wrong door");
    }

    @Override
    public void print() {
        System.out.println("GetUsers");
    }

    @Override
    public String toString() {
        return "\"Get users\" command";
    }
}
