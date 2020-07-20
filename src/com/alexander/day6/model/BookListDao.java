package com.alexander.day6.model;

import com.alexander.day6.entity.Book;
import com.alexander.day6.exception.DaoException;

import java.util.List;

public interface BookListDao {
    boolean addBook(Book book) throws DaoException;
    boolean removeBook(int id) throws DaoException;
    List<Book> sortBooksById();
    List<Book> sortBooksByTitle();
    List<Book> sortBooksByPages();
    List<Book> sortBooksByPublicationYear();
    List<Book> sortBooksByAuthors();
    List<Book> findBooksById(int id);
    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByPages(int pages);
    List<Book> findBooksByPublicationYear(int year);
    List<Book> findBooksByAuthor(String author);


}
