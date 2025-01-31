package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    private IntegerProperty bookId;
    private StringProperty title;
    private StringProperty author;
    private StringProperty genre;
    private StringProperty isbn;
    private IntegerProperty quantity;

    // Constructor
    public Book(int bookId, String title, String author, String genre, String isbn, int quantity) {
        this.bookId = new SimpleIntegerProperty(bookId);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.isbn = new SimpleStringProperty(isbn);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    // Getters and setters for properties (JavaFX bindings)
    public IntegerProperty bookIdProperty() {
        return bookId;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    // Getters and setters for actual values
    public int getBookId() {
        return bookId.get();
    }

    public void setBookId(int bookId) {
        this.bookId.set(bookId);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId.get() +
                ", title='" + title.get() + '\'' +
                ", author='" + author.get() + '\'' +
                ", genre='" + genre.get() + '\'' +
                ", isbn='" + isbn.get() + '\'' +
                ", quantity=" + quantity.get() +
                '}';
    }
}
