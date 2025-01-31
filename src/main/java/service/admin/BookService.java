package service.admin;

import dao.BookDAO;
import model.Book;

import java.util.List;

public class BookService implements BookServiceInterface {
    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(int bookId) {
        bookDAO.deleteBook(bookId);
    }
}
