package test.alexande.day6.controller.command.impl;

import com.alexander.day6.controller.command.impl.AddCommand;
import com.alexander.day6.exception.CommandException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class AddCommandTest {
    AddCommand command;

    @BeforeTest
    public void setUp() {
        command = new AddCommand();
    }

    @Test
    public void executePositiveTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("title", "Философия Java");
        parameters.put("pages", "1168");
        parameters.put("publicationYear", "1998");
        parameters.put("authors", "Брюс Эккель");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        String actualResult = null;
        try {
            actualResult = command.execute(requestParameters).get(0);
        } catch (CommandException e) {
            fail("fail test");
        }
        String expectedResult = "Book added successfully";
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test (expectedExceptions = CommandException.class)
    public void executeFullLibraryTest() throws CommandException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("title", "Философия Java");
        parameters.put("pages", "1168");
        parameters.put("publicationYear", "3100");
        parameters.put("authors", "Брюс Эккель");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        for (int i = 0; i < 2100; i++) {
            command.execute(requestParameters);
        }
    }

    @Test (expectedExceptions = CommandException.class)
    public void executeExceptionTest() throws CommandException {
        command.execute(null);
    }
}
