package com.alexander.day6.controller.command;

import com.alexander.day6.exception.CommandException;

public class ActionProvider {
    public static ActionCommand defineCommand(String command) throws CommandException {
        if (command == null || command.isBlank()) {
            throw new CommandException("No command");
        }
        ActionCommand commandType;
        try {
            commandType = CommandType.valueOf(command).getCommand();
        } catch (IllegalArgumentException e) {
            throw new CommandException("No such command");
        }
        return commandType;
    }
}
