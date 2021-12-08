package by.xenon28082.shop.controller.commands;

import by.xenon28082.shop.controller.commands.commandsImpl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandsMap {

    private Map<String, Command> COMMANDS = new HashMap<String, Command>();

    public CommandsMap() {
        initMap();
    }

    private void initMap(){
        COMMANDS.put("CREATE_NEW_USER", new CreateNewUserCommand());
        COMMANDS.put("FIND_USER", new LoginCommand());
        COMMANDS.put("GET_PRODUCTS", new GetProductsCommand());
        COMMANDS.put("ADD_TO_BASKET", new AddToBasketCommand());
        COMMANDS.put("SHOW_BASKET", new ShowUserBasketCommand());
        COMMANDS.put("DELETE_FROM_BASKET", new DeleteFromBasketCommand());
        COMMANDS.put("LOGOUT", new LogoutCommand());
        COMMANDS.put("ADD_NEW_ITEM", new AddNewItemCommand());
        COMMANDS.put("GET_VENDORS", new GetVendorsCommand());
        COMMANDS.put("DELETE_SOME", new DeleteItemCommand());
        COMMANDS.put("GET_USER_INFO", new FindUserCommand());
        COMMANDS.put("UPDATE_USER", new UpdateUserRoleCommand());
        COMMANDS.put("ADD_SOME", new AddItemCommand());
        COMMANDS.put("UPDATE_USER_LOGIN", new UpdateUserLoginCommand());
        COMMANDS.put("UPDATE_USER_PASSWORD", new UpdateUserPasswordCommand());
        COMMANDS.put("RESERVATE_PRODUCT", new ReservateProductCommand());
        COMMANDS.put("SHOW_ORDER", new ShowOrderCommand());
        COMMANDS.put("CLOSE_ORDER", new CloseOrderCommand());
        COMMANDS.put("GET_ORDERS", new ControlOrderCommand());
        COMMANDS.put("ACCEPT_FINAL_ORDER", new AcceptFinalOrderCommand());
        COMMANDS.put("REFUSE_FINAL_ORDER", new RefuseFinalOrderCommand());
        COMMANDS.put("CHANGE_LANG", new ChangeLocaleCommand());
        COMMANDS.put("TEST_COMMAND", new TestCommand());
    }

    public Command getCommand(String command){
        return COMMANDS.get(command);
    }

}
