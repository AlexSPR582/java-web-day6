package test.alexande.day6.controller.command.impl;

import com.alexander.day6.controller.command.impl.SortIdCommand;
import com.alexander.day6.entity.Book;
import com.alexander.day6.entity.Library;
import com.alexander.day6.exception.CommandException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class SortIdCommandTest {
    SortIdCommand command;

    @BeforeTest
    public void setUp() {
        command = new SortIdCommand();
    }

    @Test
    public void executePositiveTest() {
        List<String> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book1.toString());
        expectedResult.add(book2.toString());
        expectedResult.add(book3.toString());
        expectedResult.add(book4.toString());
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        List<String> actualResult = null;
        Optional<Map<String, String>> requestParameters = Optional.empty();
        try {
            actualResult = command.execute(requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void executeNegativeTest() {
        List<String> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book1.toString());
        expectedResult.add(book4.toString());
        expectedResult.add(book2.toString());
        expectedResult.add(book3.toString());
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book4);
        library.addBook(book3);
        List<String> actualResult = null;
        Optional<Map<String, String>> requestParameters = Optional.empty();
        try {
            actualResult = command.execute(requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test (expectedExceptions = CommandException.class)
    public void executeExceptionTest() throws CommandException {
        command.execute(null);
    }
}
