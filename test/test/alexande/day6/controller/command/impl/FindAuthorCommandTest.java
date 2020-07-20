package test.alexande.day6.controller.command.impl;

import com.alexander.day6.controller.command.impl.FindAuthorCommand;
import com.alexander.day6.entity.Book;
import com.alexander.day6.entity.Library;
import com.alexander.day6.exception.CommandException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class FindAuthorCommandTest {
    FindAuthorCommand command;

    @BeforeTest
    public void setUp() {
        command = new FindAuthorCommand();
    }

    @Test
    public void executePositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "Оскар Уайльд");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = command.execute(requestParameters).get(0);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void executeNegativeTest() {
        List<String> actualResult = null;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "Стивен Кинг");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = command.execute(requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test (expectedExceptions = CommandException.class)
    public void executeExceptionTest() throws CommandException {
        command.execute(null);
    }
}
