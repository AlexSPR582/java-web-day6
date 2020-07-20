package com.alexander.day6.controller;

import com.alexander.day6.controller.command.ActionProvider;
import com.alexander.day6.controller.command.ActionCommand;
import com.alexander.day6.exception.CommandException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Controller {
    public List<String> processRequest(String commandType,
                                       Optional<Map<String, String>> requestParameters)
            throws CommandException {
        ActionCommand command = ActionProvider.defineCommand(commandType);
        return command.execute(requestParameters);
    }
}
