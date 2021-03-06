package com.alexander.day6.controller.command.impl;

import com.alexander.day6.controller.command.ActionCommand;
import com.alexander.day6.exception.CommandException;
import com.alexander.day6.exception.ServiceException;
import com.alexander.day6.service.BookService;
import com.alexander.day6.validator.RequestValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FindPagesCommand implements ActionCommand {
    @Override
    public List<String> execute(Optional<Map<String, String>> requestParameters)
            throws CommandException {
        RequestValidator validator = new RequestValidator();
        if (!validator.findRequestValidation(requestParameters)) {
            throw new CommandException("Invalid Parameters");
        }
        Map<String, String> parameters = requestParameters.get();
        String pages = parameters.get("tagValue");
        BookService service = new BookService();
        List<String> response;
        try {
            response = service.findBooksByPages(pages);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
