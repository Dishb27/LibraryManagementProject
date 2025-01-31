package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Date;

public class Borrow {
    private IntegerProperty bookId;
    private StringProperty bookTitle;
    private ObjectProperty<Date> borrowedDate;
    private ObjectProperty<Date> dueDate;

    public Borrow(int bookId, String bookTitle, Date borrowedDate, Date dueDate) {
        this.bookId = new SimpleIntegerProperty(bookId);
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.borrowedDate = new SimpleObjectProperty<>(borrowedDate);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
    }

    // Getters and setters for properties
    public IntegerProperty bookIdProperty() {
        return bookId;
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public ObjectProperty<Date> borrowedDateProperty() {
        return borrowedDate;
    }

    public ObjectProperty<Date> dueDateProperty() {
        return dueDate;
    }

    // Getters and setters for actual values
    public int getBookId() {
        return bookId.get();
    }

    public void setBookId(int bookId) {
        this.bookId.set(bookId);
    }

    public String getBookTitle() {
        return bookTitle.get();
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public Date getBorrowedDate() {
        return borrowedDate.get();
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate.set(borrowedDate);
    }

    public Date getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(Date dueDate) {
        this.dueDate.set(dueDate);
    }
    @Override
    public String toString() {
        return "Borrow{" +
                "bookId=" + bookId.get() +
                ", bookTitle=" + bookTitle.get() +
                ", borrowedDate=" + borrowedDate.get() +
                ", dueDate=" + dueDate.get() +
                '}';
    }
}