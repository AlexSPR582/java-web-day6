package com.alexander.day6.service;

import com.alexander.day6.entity.Book;
import com.alexander.day6.exception.DaoException;
import com.alexander.day6.exception.ServiceException;
import com.alexander.day6.model.BookListDao;
import com.alexander.day6.model.impl.BookListDaoImpl;
import com.alexander.day6.validator.BookValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookService {
    public List<String> addBook(String title, String pages, String publicationYear, String authors)
            throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.bookTitleValidation(title) ||
                !validator.bookPagesValidation(pages) ||
                !validator.bookPublicationYearValidation(publicationYear) ||
                !validator.bookAuthorsValidation(authors)) {
            throw new ServiceException("Invalid request parameters");
        }
        int bookPages = Integer.parseInt(pages);
        int bookPublicationYear = Integer.parseInt(publicationYear);
        List<String> bookAuthors = Arrays.asList(authors.split(";"));
        Book book = new Book(title, bookPages, bookPublicationYear, bookAuthors);
        BookListDao dao = new BookListDaoImpl();
        List<String> response = new ArrayList<>();
        try {
            dao.addBook(book);
            String message = "Book added successfully";
            response.add(message);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
    }

    public List<String> removeBook(String id)
            throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.bookIdValidation(id)) {
            throw new ServiceException("Invalid request parameters");
        }
        int bookId = Integer.parseInt(id);
        BookListDao dao = new BookListDaoImpl();
        List<String> response = new ArrayList<>();
        try {
            dao.removeBook(bookId);
            String message = "Book removed successfully";
            response.add(message);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
    }

    public List<String> findBooksById(String id) throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.bookIdValidation(id)) {
            throw new ServiceException("Invalid request parameters");
        }
        int bookId = Integer.parseInt(id);
        BookListDao dao = new BookListDaoImpl();
        List<Book> foundBooks = dao.findBooksById(bookId);
        List<String> response = new ArrayList<>();
        for (Book book: foundBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> findBooksByTitle(String title) throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.bookTitleValidation(title)) {
            throw new ServiceException("Invalid request parameters");
        }
        BookListDao dao = new BookListDaoImpl();
        List<Book> foundBooks = dao.findBooksByTitle(title);
        List<String> response = new ArrayList<>();
        for (Book book: foundBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> findBooksByPages(String pages) throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.bookPagesValidation(pages)) {
            throw new ServiceException("Invalid request parameters");
        }
        int bookPages = Integer.parseInt(pages);
        BookListDao dao = new BookListDaoImpl();
        List<Book> foundBooks = dao.findBooksByPages(bookPages);
        List<String> response = new ArrayList<>();
        for (Book book: foundBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> findBooksByPublicationYear(String year) throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.bookPublicationYearValidation(year)) {
            throw new ServiceException("Invalid request parameters");
        }
        int publicationYear = Integer.parseInt(year);
        BookListDao dao = new BookListDaoImpl();
        List<Book> foundBooks = dao.findBooksByPublicationYear(publicationYear);
        List<String> response = new ArrayList<>();
        for (Book book: foundBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> findBooksByAuthor(String author) throws ServiceException {
        if (author == null) {
            throw new ServiceException("author is null");
        }
        BookListDao dao = new BookListDaoImpl();
        List<Book> foundBooks = dao.findBooksByAuthor(author);
        List<String> response = new ArrayList<>();
        for (Book book: foundBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> sortBooksById() {
        BookListDao dao = new BookListDaoImpl();
        List<Book> sortBooks = dao.sortBooksById();
        List<String> response = new ArrayList<>();
        for (Book book: sortBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> sortBooksByTitle() {
        BookListDao dao = new BookListDaoImpl();
        List<Book> sortBooks = dao.sortBooksByTitle();
        List<String> response = new ArrayList<>();
        for (Book book: sortBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> sortBooksByPages() {
        BookListDao dao = new BookListDaoImpl();
        List<Book> sortBooks = dao.sortBooksByPages();
        List<String> response = new ArrayList<>();
        for (Book book: sortBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> sortBooksByAuthors() {
        BookListDao dao = new BookListDaoImpl();
        List<Book> sortBooks = dao.sortBooksByAuthors();
        List<String> response = new ArrayList<>();
        for (Book book: sortBooks) {
            response.add(book.toString());
        }
        return response;
    }

    public List<String> sortBooksByPublicationYear() {
        BookListDao dao = new BookListDaoImpl();
        List<Book> sortBooks = dao.sortBooksByPublicationYear();
        List<String> response = new ArrayList<>();
        for (Book book: sortBooks) {
            response.add(book.toString());
        }
        return response;
    }
}
