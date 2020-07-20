package test.alexande.day6.model.impl;

import com.alexander.day6.entity.Book;
import com.alexander.day6.exception.DaoException;
import com.alexander.day6.model.impl.BookListDaoImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class BookListDaoTest {
    BookListDaoImpl dao;

    @BeforeTest
    public void setUp() {
        dao = new BookListDaoImpl();
    }

    @Test
    public void addBookPositiveTest() {
        Book book = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        boolean actualResult = false;
        try {
            actualResult = dao.addBook(book);
        } catch (DaoException e) {
            fail("fail test");
        }
        assertTrue(actualResult, "fail test");
    }

    @Test (expectedExceptions = DaoException.class)
    public void addBookFullLibraryTest() throws DaoException {
        Book book = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        for (int i = 0; i < 2100; i++) {
            dao.addBook(book);
        }
    }

    @Test
    public void removeBookPositiveTest() {
        Book book = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        boolean actualResult = false;
        try {
            dao.addBook(book);
            actualResult = dao.removeBook(1);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        assertTrue(actualResult, "fail test");
    }

    @Test (expectedExceptions = DaoException.class)
    public void removeBookNegative() throws DaoException {
        dao.removeBook(45);
    }

    @Test (expectedExceptions = DaoException.class)
    public void removeBooksExceptionTest() throws DaoException{
        dao.removeBook(45);
    }

    @Test
    public void findBooksByAuthorPositiveTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> expectedResult = List.of(book2);
        List<Book> actualResult = dao.findBooksByAuthor("Оскар Уайльд");
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByAuthorNegativeTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.findBooksByAuthor("Стивен Кинг");;
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void findBooksByIdPositiveTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> expectedResult = List.of(book2);
        List<Book> actualResult = dao.findBooksById(2);
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByIdNegativeTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.findBooksById(5);
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void findBooksByTitlePositiveTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> expectedResult = List.of(book2);
        List<Book> actualResult = dao.findBooksByTitle("Портрет Дориана Грея");
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByTitleNegativeTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.findBooksByTitle("Война и мир");
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void findBooksByPagesPositiveTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> expectedResult = List.of(book2);
        List<Book> actualResult = dao.findBooksByPages(384);
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByPagesNegativeTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.findBooksByPages(456);
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void findBooksByPublicationYearPositiveTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> expectedResult = List.of(book2);
        List<Book> actualResult = dao.findBooksByPublicationYear(1947);
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void findBooksByPublicationYearNegativeTest() {
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        try {
            dao.addBook(book1);
            dao.addBook(book2);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.findBooksByPublicationYear(2007);
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sortBooksByIdPositiveTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book1);
        expectedResult.add(book2);
        expectedResult.add(book3);
        expectedResult.add(book4);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksById();
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByIdNegativeTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book1);
        expectedResult.add(book3);
        expectedResult.add(book2);
        expectedResult.add(book4);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksById();
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByTitlePositiveTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book3);
        expectedResult.add(book4);
        expectedResult.add(book2);
        expectedResult.add(book1);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksByTitle();
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByTitleNegativeTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book2);
        expectedResult.add(book1);
        expectedResult.add(book3);
        expectedResult.add(book4);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksByTitle();
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByPagesPositiveTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book2);
        expectedResult.add(book3);
        expectedResult.add(book4);
        expectedResult.add(book1);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksByPages();
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByPagesNegativeTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book2);
        expectedResult.add(book1);
        expectedResult.add(book3);
        expectedResult.add(book4);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksByPages();
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByPublicationYearPositiveTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book2);
        expectedResult.add(book4);
        expectedResult.add(book1);
        expectedResult.add(book3);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksByPublicationYear();
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByPublicationYearNegativeTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book2);
        expectedResult.add(book1);
        expectedResult.add(book3);
        expectedResult.add(book4);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksByPublicationYear();
        assertNotEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByAuthorsPositiveTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book1);
        expectedResult.add(book2);
        expectedResult.add(book4);
        expectedResult.add(book3);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksByAuthors();
        assertEquals(actualResult, expectedResult, "fail test");
    }

    @Test
    public void sortBooksByAuthorsNegativeTest() {
        List<Book> expectedResult = new ArrayList<>();
        Book book1 = new Book("Философия Java", 1168, 1998, Collections.singletonList("Брюс Эккель"));
        Book book2 = new Book("Портрет Дориана Грея", 384, 1947, Collections.singletonList("Оскар Уайльд"));
        Book book3 = new Book("Двенадцать стульев", 416, 2009, List.of("Илья Арнольдович Ильф", "Евгений Петрович Петров"));
        Book book4 = new Book("Мастер и Маргарита", 480, 1966, Collections.singletonList("Михаил Булгаков"));
        expectedResult.add(book2);
        expectedResult.add(book1);
        expectedResult.add(book3);
        expectedResult.add(book4);
        try {
            dao.addBook(book1);
            dao.addBook(book2);
            dao.addBook(book3);
            dao.addBook(book4);
        } catch (DaoException e) {
            fail("Exception occurred");
        }
        List<Book> actualResult = dao.sortBooksByAuthors();
        assertNotEquals(actualResult, expectedResult, "fail test");
    }
}
