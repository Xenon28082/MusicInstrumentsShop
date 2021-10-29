package by.xenon28082.shop.controller.commands;

import by.xenon28082.shop.controller.commands.commandsImpl.LoginCommand;
import by.xenon28082.shop.dao.impl.ProductDAOImpl;
import by.xenon28082.shop.dao.impl.UserDAOImpl;
import by.xenon28082.shop.entity.User;

import java.sql.SQLException;
import java.util.Map;

public class CommandsMap {

    private static final Map<String, Command> COMMANDS = CommandsFiller.initCommandMap();

    public static void main(String[] args) throws SQLException {

        ProductDAOImpl dao = new ProductDAOImpl();
//        dao.findAllProducts();

//        UserDAOImpl dao = new UserDAOImpl();
//        dao.find(new User("Mud", "primus"));
    }

    public static Command getCommand(String command){
        return COMMANDS.get(command);
    }

}
