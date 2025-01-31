package dao;

import db.DBConnection;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    // Get all books from the database
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("genre"),
                        resultSet.getString("isbn"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("availability")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Update a book in the database
    public void updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, genre = ?, isbn = ?, quantity = ? WHERE book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setString(4, book.getIsbn());
            statement.setInt(5, book.getQuantity());
            statement.setInt(6, book.getBookId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a book from the database
    public void deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new book to the database
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, genre, isbn, quantity) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setString(4, book.getIsbn());
            statement.setInt(5, book.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
