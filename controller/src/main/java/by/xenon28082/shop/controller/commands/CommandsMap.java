package by.xenon28082.shop.controller.commands;

import java.util.Map;

public class CommandsMap {

    private static final Map<String, Command> COMMANDS = CommandsFiller.initCommandMap();

    public static Command getCommand(String command){
        return COMMANDS.get(command);
    }

}
