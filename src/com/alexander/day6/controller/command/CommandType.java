package com.alexander.day6.controller.command;

import com.alexander.day6.controller.command.ActionCommand;
import com.alexander.day6.controller.command.impl.AddCommand;
import com.alexander.day6.controller.command.impl.RemoveCommand;
import com.alexander.day6.controller.command.impl.SortIdCommand;
import com.alexander.day6.controller.command.impl.SortTitleCommand;
import com.alexander.day6.controller.command.impl.SortPagesCommand;
import com.alexander.day6.controller.command.impl.SortPublicationYearCommand;
import com.alexander.day6.controller.command.impl.SortAuthorsCommand;
import com.alexander.day6.controller.command.impl.FindIdCommand;
import com.alexander.day6.controller.command.impl.FindTitleCommand;
import com.alexander.day6.controller.command.impl.FindPagesCommand;
import com.alexander.day6.controller.command.impl.FindPublicationYearCommand;
import com.alexander.day6.controller.command.impl.FindAuthorCommand;

public enum CommandType {
    ADD (new AddCommand()),
    REMOVE (new RemoveCommand()),
    SORT_ID (new SortIdCommand()),
    SORT_TITLE (new SortTitleCommand()),
    SORT_PAGES (new SortPagesCommand()),
    SORT_PUBLICATION_YEAR (new SortPublicationYearCommand()),
    SORT_AUTHORS (new SortAuthorsCommand()),
    FIND_ID (new FindIdCommand()),
    FIND_TITLE (new FindTitleCommand()),
    FIND_PAGES (new FindPagesCommand()),
    FIND_PUBLICATION_YEAR (new FindPublicationYearCommand()),
    FIND_AUTHOR(new FindAuthorCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
