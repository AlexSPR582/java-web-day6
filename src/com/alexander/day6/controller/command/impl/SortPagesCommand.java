package com.alexander.day6.controller.command.impl;

import com.alexander.day6.controller.command.ActionCommand;
import com.alexander.day6.exception.CommandException;
import com.alexander.day6.service.BookService;
import com.alexander.day6.validator.RequestValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SortPagesCommand implements ActionCommand {
    @Override
    public List<String> execute(Optional<Map<String, String>> requestParameters)
            throws CommandException {
        RequestValidator validator = new RequestValidator();
        if (!validator.sortRequestValidation(requestParameters)) {
            throw new CommandException("Invalid Parameters");
        }
        BookService service = new BookService();
        List<String> response = service.sortBooksByPages();
        return response;
    }
}
