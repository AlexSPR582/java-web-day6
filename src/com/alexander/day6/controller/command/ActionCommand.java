package com.alexander.day6.controller.command;

import com.alexander.day6.exception.CommandException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ActionCommand {
    List<String> execute(Optional<Map<String, String>> parameters) throws CommandException;
}
