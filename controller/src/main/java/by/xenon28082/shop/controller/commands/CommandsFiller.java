package by.xenon28082.shop.controller.commands;

import by.xenon28082.shop.controller.commands.commandsImpl.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandsFiller {

    private static Map<String, Command> commandMap = new HashMap<String, Command>();

    public static Map<String, Command> initCommandMap(){

        commandMap.put("GET_USERS", new GetUsersCommand());
        commandMap.put("CREATE_NEW_USER", new CreateNewUserCommand());
        commandMap.put("COUNT_USERS", new CountUsersCommand());
        commandMap.put("FIND_USER", new LoginCommand());
        commandMap.put("GET_PRODUCTS", new GetProductsCommand());
        commandMap.put("ADD_TO_BASKET", new AddToBasketCommand());
        commandMap.put("SHOW_BASKET", new ShowUserBasketCommand());
        commandMap.put("DELETE_FROM_BASKET", new DeleteFromBasketCommand());

        return commandMap;
    }

}
