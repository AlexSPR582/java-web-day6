package test.alexande.day6.controller;

import com.alexander.day6.controller.Controller;
import com.alexander.day6.entity.Book;
import com.alexander.day6.entity.Library;
import com.alexander.day6.exception.CommandException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class ControllerTest {
    Controller controller;

    @BeforeTest
    public void setUp() {
        controller = new Controller();
    }

    @Test
    public void processAddRequestPositiveTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("title", "Философия Java");
        parameters.put("pages", "1168");
        parameters.put("publicationYear", "1998");
        parameters.put("authors", "Брюс Эккель");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        String actualResult = null;
        try {
            actualResult = controller.processRequest("ADD", requestParameters).get(0);
        } catch (CommandException e) {
            fail("fail test");
        }
        String expectedResult = "Book added successfully";
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test (expectedExceptions = CommandException.class)
    public void processAddRequestNegativeTest() throws CommandException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("title", "Философия Java");
        parameters.put("pages", "1168");
        parameters.put("publicationYear", "3100");
        parameters.put("authors", "Брюс Эккель");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        for (int i = 0; i < 2100; i++) {
            controller.processRequest("ADD", requestParameters);
        }
    }

    @Test
    public void processRemoveBookPositiveTest() {
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
            actualResult = controller.processRequest("REMOVE", requestParameters).get(0);
        } catch (CommandException e) {
            fail("fail test");
        }
        String expectedResult = "Book removed successfully";
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test (expectedExceptions = CommandException.class)
    public void processRemoveBookNegativeTest() throws CommandException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "2");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        controller.processRequest("REMOVE", requestParameters);
    }

    @Test
    public void processFindBooksByAuthorPositiveTest() {
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
            actualResult = controller.processRequest("FIND_AUTHOR", requestParameters).get(0);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processFindBooksByAuthorNegativeTest() {
        List<String> actualResult = null;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "Стивен Кинг");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_AUTHOR", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void processFindBooksByIdPositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "2");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_ID", requestParameters).get(0);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processFindBooksByIdNegativeTest() {
        List<String> actualResult = null;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "34");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_ID", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void processFindBooksByPagesPositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "384");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_PAGES", requestParameters).get(0);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processFindBooksByPagesNegativeTest() {
        List<String> actualResult = null;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "347");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_PAGES", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void processFindBooksByPublicationYearPositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "1947");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_PUBLICATION_YEAR", requestParameters).get(0);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processFindBooksByPublicationYearNegativeTest() {
        List<String> actualResult = null;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "1347");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_PUBLICATION_YEAR", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void processFindBooksByTitlePositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "Портрет Дориана Грея");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_TITLE", requestParameters).get(0);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processFindBooksByTitleNegativeTest() {
        List<String> actualResult = null;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "Война и мир");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        try {
            actualResult = controller.processRequest("FIND_TITLE", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void processSortBooksByAuthorsPositiveTest() {
        List<String> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book1.toString());
        expectedResult.add(book2.toString());
        expectedResult.add(book4.toString());
        expectedResult.add(book3.toString());
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        List<String> actualResult = null;
        Optional<Map<String, String>> requestParameters = Optional.empty();
        try {
            actualResult = controller.processRequest("SORT_AUTHORS", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void processSortBooksByAuthorsNegativeTest() {
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
            actualResult = controller.processRequest("SORT_AUTHORS", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processSortBooksByIdPositiveTest() {
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
            actualResult = controller.processRequest("SORT_ID", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void processSortBooksByIdNegativeTest() {
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
            actualResult = controller.processRequest("SORT_ID", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processSortBooksByPagesPositiveTest() {
        List<String> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book2.toString());
        expectedResult.add(book3.toString());
        expectedResult.add(book4.toString());
        expectedResult.add(book1.toString());
        Library library = Library.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        List<String> actualResult = null;
        Optional<Map<String, String>> requestParameters = Optional.empty();
        try {
            actualResult = controller.processRequest("SORT_PAGES", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void processSortBooksByPagesNegativeTest() {
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
            actualResult = controller.processRequest("SORT_PAGES", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processSortBooksByPublicationYearPositiveTest() {
        List<String> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book2.toString());
        expectedResult.add(book4.toString());
        expectedResult.add(book1.toString());
        expectedResult.add(book3.toString());
        Library library = Library.getInstance();
        library.addBook(book2);
        library.addBook(book4);
        library.addBook(book1);
        library.addBook(book3);
        List<String> actualResult = null;
        Optional<Map<String, String>> requestParameters = Optional.empty();
        try {
            actualResult = controller.processRequest("SORT_PUBLICATION_YEAR", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void processSortBooksByPublicationYearNegativeTest() {
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
            actualResult = controller.processRequest("SORT_PUBLICATION_YEAR", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void processSortBooksByTitlePositiveTest() {
        List<String> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book3.toString());
        expectedResult.add(book4.toString());
        expectedResult.add(book2.toString());
        expectedResult.add(book1.toString());
        Library library = Library.getInstance();
        library.addBook(book2);
        library.addBook(book4);
        library.addBook(book1);
        library.addBook(book3);
        List<String> actualResult = null;
        Optional<Map<String, String>> requestParameters = Optional.empty();
        try {
            actualResult = controller.processRequest("SORT_TITLE", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void processSortBooksByTitleNegativeTest() {
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
            actualResult = controller.processRequest("SORT_TITLE", requestParameters);
        } catch (CommandException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test(expectedExceptions = CommandException.class)
    public void processRequestExceptionTest() throws CommandException {
        controller.processRequest(null, null);
    }
}
