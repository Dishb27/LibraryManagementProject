package service.admin;

import model.Book;

import java.util.List;

public interface BookServiceInterface {
    List<Book> getAllBooks();
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int bookId);
}