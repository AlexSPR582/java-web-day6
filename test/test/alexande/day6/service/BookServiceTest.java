package test.alexande.day6.service;

import static org.testng.Assert.fail;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import com.alexander.day6.exception.ServiceException;
import com.alexander.day6.service.BookService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {
    BookService bookService;

    @BeforeTest
    public void setUp() {
        bookService = new BookService();
    }

    @Test
    public void addBookPositiveTest() {
        String title = "Философия Java";
        String pages = "1168";
        String publishYear = "1998";
        String authors = "Брюс Эккель";
        String actualResult = null;
        try {
            actualResult = bookService.addBook(title, pages, publishYear, authors).get(0);
        } catch (ServiceException e) {
            fail("fail test");
        }
        String expectedResult = "Book added successfully";
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test (expectedExceptions = ServiceException.class)
    public void addBookFullLibraryTest() throws ServiceException {
        String title = "Философия Java";
        String pages = "1168";
        String publishYear = "1998";
        String authors = "Брюс Эккель";
        for (int i = 0; i < 2100; i++) {
            bookService.addBook(title, pages, publishYear, authors);
        }
    }

    @Test (expectedExceptions = ServiceException.class)
    public void addBookInvalidParametersExceptionTest() throws ServiceException {
        String title = "Философия Java";
        String pages = "1168ABV";
        String publishYear = "1998";
        String authors = "Брюс Эккель";
        bookService.addBook(title, pages, publishYear, authors);
    }

    @Test
    public void removeBookPositiveTest() {
        String title = "Философия Java";
        String pages = "1168";
        String publishYear = "1998";
        String authors = "Брюс Эккель";
        String actualResult = null;
        try {
            bookService.addBook(title, pages, publishYear, authors);
            actualResult = bookService.removeBook("1").get(0);
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        String expectedResult = "Book removed successfully";
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void removeBookNegative() {
        String actualResult = null;
        String expectedResult = "com.alexander.day6.exception.DaoException: Book not removed";
        try {
            bookService.removeBook("2100");
            fail("Exception not occurred");
        } catch (ServiceException e) {
            actualResult = e.getMessage();
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test (expectedExceptions = ServiceException.class)
    public void removeBooksExceptionTest() throws ServiceException{
        bookService.removeBook("A");
    }

    @Test
    public void findBooksByAuthorPositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksByAuthor("Оскар Уайльд").get(0);
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByAuthorNegativeTest() {
        List<String> actualResult = null;
        try {
            actualResult = bookService.findBooksByAuthor("Стивен Кинг");
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test (expectedExceptions = ServiceException.class)
    public void findBooksByAuthorExceptionTest() throws ServiceException {
        bookService.findBooksByAuthor(null);
    }

    @Test
    public void findBooksByIdPositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksById("2").get(0);
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByIdNegativeTest() {
        List<String> actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksById("7");
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test (expectedExceptions = ServiceException.class)
    public void findBooksByIdExceptionTest() throws ServiceException {
        bookService.findBooksById("ABC");
    }

    @Test
    public void findBooksByTitlePositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksByTitle("Портрет Дориана Грея").get(0);
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByTitleNegativeTest() {
        List<String> actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksByTitle("Война и мир");
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test (expectedExceptions = ServiceException.class)
    public void findBooksByTitleExceptionTest() throws ServiceException {
        bookService.findBooksByTitle(null);
    }

    @Test
    public void findBooksByPagesPositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksByPages("384").get(0);
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByPagesNegativeTest() {
        List<String> actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksByPages("2120");
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test (expectedExceptions = ServiceException.class)
    public void findBooksByPagesExceptionTest() throws ServiceException {
        bookService.findBooksByPages("A");
    }

    @Test
    public void findBooksByPublicationYearPositiveTest() {
        String expectedResult = "Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}";
        String actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksByPublicationYear("1947").get(0);
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByPublicationYearNegativeTest() {
        List<String> actualResult = null;
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.findBooksByPublicationYear("1567");
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult.isEmpty());
    }

    @Test (expectedExceptions = ServiceException.class)
    public void findBooksByPublicationYearExceptionTest() throws ServiceException {
        bookService.findBooksByPublicationYear("3467");
    }

    @Test
    public void sortBooksByIdPositiveTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        expectedResult.add("Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=3, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        expectedResult.add("Book{id=4, title='Гордость и предубеждение', authors=[Джейн Остин], pages=416, publicationYear=2005}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.sortBooksById();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByIdNegativeTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        expectedResult.add("Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=4, title='Гордость и предубеждение', authors=[Джейн Остин], pages=416, publicationYear=2005}");
        expectedResult.add("Book{id=3, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.sortBooksById();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByTitlePositiveTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=4, title='Гордость и предубеждение', authors=[Джейн Остин], pages=416, publicationYear=2005}");
        expectedResult.add("Book{id=3, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        expectedResult.add("Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.sortBooksByTitle();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByTitleNegativeTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=3, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        expectedResult.add("Book{id=4, title='Гордость и предубеждение', authors=[Джейн Остин], pages=416, publicationYear=2005}");
        expectedResult.add("Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.sortBooksByTitle();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByPagesPositiveTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=4, title='Гордость и предубеждение', authors=[Джейн Остин], pages=416, publicationYear=2005}");
        expectedResult.add("Book{id=3, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.sortBooksByPages();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByPagesNegativeTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        expectedResult.add("Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=4, title='Гордость и предубеждение', authors=[Джейн Остин], pages=416, publicationYear=2005}");
        expectedResult.add("Book{id=3, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.sortBooksByPages();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByPublicationYearPositiveTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=3, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        expectedResult.add("Book{id=4, title='Гордость и предубеждение', authors=[Джейн Остин], pages=416, publicationYear=2005}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.sortBooksByPublicationYear();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByPublicationYearNegativeTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        expectedResult.add("Book{id=2, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=4, title='Гордость и предубеждение', authors=[Джейн Остин], pages=416, publicationYear=2005}");
        expectedResult.add("Book{id=3, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            bookService.addBook("Гордость и предубеждение", "416", "2005", "Джейн Остин");
            actualResult = bookService.sortBooksByPublicationYear();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByAuthorsPositiveTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        expectedResult.add("Book{id=3, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=4, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        expectedResult.add("Book{id=2, title='Двенадцать стульев', authors=[Илья Арнольдович Ильф, Евгений Петрович Петров], pages=416, publicationYear=2009}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Двенадцать стульев", "416", "2009", "Илья Арнольдович Ильф;Евгений Петрович Петров");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            actualResult = bookService.sortBooksByAuthors();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByAuthorsNegativeTest() {
        List<String> actualResult = null;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Book{id=1, title='Философия Java', authors=[Брюс Эккель], pages=1168, publicationYear=1998}");
        expectedResult.add("Book{id=2, title='Двенадцать стульев', authors=[Илья Арнольдович Ильф, Евгений Петрович Петров], pages=416, publicationYear=2009}");
        expectedResult.add("Book{id=3, title='Портрет Дориана Грея', authors=[Оскар Уайльд], pages=384, publicationYear=1947}");
        expectedResult.add("Book{id=4, title='Мастер и Маргарита', authors=[Михаил Булгаков], pages=480, publicationYear=1966}");
        try {
            bookService.addBook("Философия Java", "1168", "1998", "Брюс Эккель");
            bookService.addBook("Двенадцать стульев", "416", "2009", "Илья Арнольдович Ильф;Евгений Петрович Петров");
            bookService.addBook("Портрет Дориана Грея", "384", "1947", "Оскар Уайльд");
            bookService.addBook("Мастер и Маргарита", "480", "1966", "Михаил Булгаков");
            actualResult = bookService.sortBooksByAuthors();
        } catch (ServiceException e) {
            fail("Exception occurred");
        }
        assertNotEquals(actualResult, expectedResult, "fail test");
    }
}
