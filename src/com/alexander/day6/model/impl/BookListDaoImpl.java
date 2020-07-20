package com.alexander.day6.model.impl;

import com.alexander.day6.entity.Book;
import com.alexander.day6.entity.Library;
import com.alexander.day6.exception.DaoException;
import com.alexander.day6.model.BookListDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookListDaoImpl implements BookListDao {
    @Override
    public boolean addBook(Book book) throws DaoException {
        Library library = Library.getInstance();
        if (!library.addBook(book)) {
            throw new DaoException("Book not added");
        }
        return true;
    }

    @Override
    public boolean removeBook(int id) throws DaoException {
        Library library = Library.getInstance();
        List<Book> books = library.getBooks();
        Book removedBook = null;
        for (Book book: books) {
            if (book.getId() == id) {
                removedBook = book;
            }
        }
        if (removedBook == null || !library.removeBook(removedBook)) {
            throw new DaoException("Book not removed");
        }
        return true;
    }

    @Override
    public List<Book> sortBooksById() {
        Comparator<Book> idComparator = Comparator.comparingInt(Book::getId);
        List<Book> books = new ArrayList<>(Library.getInstance().getBooks());
        books.sort(idComparator);
        return books;
    }

    @Override
    public List<Book> sortBooksByTitle() {
        List<Book> books = new ArrayList<>(Library.getInstance().getBooks());
        Comparator<Book> titleComparator = (b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle());
        books.sort(titleComparator);
        return books;
    }

    @Override
    public List<Book> sortBooksByPages() {
        Comparator<Book> pageComparator = Comparator.comparingInt(Book::getPages);
        List<Book> books = new ArrayList<>(Library.getInstance().getBooks());
        books.sort(pageComparator);
        return books;
    }

    @Override
    public List<Book> sortBooksByAuthors() {
        List<Book> books = new ArrayList<>(Library.getInstance().getBooks());
        Comparator<Book> authorsComparator = Comparator.comparingInt(b -> b.getAuthors().size());
        books.sort(authorsComparator);
        return books;
    }

    @Override
    public List<Book> sortBooksByPublicationYear() {
        Comparator<Book> yearComparator = Comparator.comparingInt(Book::getPublicationYear);
        List<Book> books = new ArrayList<>(Library.getInstance().getBooks());
        books.sort(yearComparator);
        return books;
    }

    @Override
    public List<Book> findBooksById(int id) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getId() == id) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getTitle().equals(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> findBooksByPages(int pages) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getPages() == pages) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> findBooksByPublicationYear(int year) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getPublicationYear() == year) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> findBooksByAuthor(String bookAuthor) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = new ArrayList<>();
        for (Book book: books) {
            List<String> authors = book.getAuthors();
            for (String author: authors) {
                if (author.equals(bookAuthor)) {
                    foundBooks.add(book);
                    break;
                }
            }
        }
        return foundBooks;
    }
}
