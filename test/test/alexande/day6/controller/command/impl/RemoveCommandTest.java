package test.alexande.day6.controller.command.impl;

import com.alexander.day6.controller.command.impl.RemoveCommand;
import com.alexander.day6.entity.Book;
import com.alexander.day6.entity.Library;
import com.alexander.day6.exception.CommandException;
import com.alexander.day6.exception.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class RemoveCommandTest {
    RemoveCommand command;

    @BeforeTest
    public void setUp() {
        command = new RemoveCommand();
    }

    @Test
    public void executePositiveTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "2");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        String actualResult = null;
        try {
            actualResult = command.execute(requestParameters).get(0);
        } catch (CommandException e) {
            fail("fail test");
        }
        String expectedResult = "Book removed successfully";
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test (expectedExceptions = CommandException.class)
    public void executeNegativeTest() throws CommandException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "2");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        command.execute(requestParameters);
    }

    @Test (expectedExceptions = ServiceException.class)
    public void executeExceptionTest() throws CommandException{
        command.execute(null);
    }
}
