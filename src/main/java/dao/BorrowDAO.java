package dao;

import db.DBConnection;
import model.Borrow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {
    private Connection connection;

    public BorrowDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    // Get all borrowed books for a specific user with book title
    public List<Borrow> getBorrowedBooksByUserId(int userId) {
        List<Borrow> borrows = new ArrayList<>();
        String query = "SELECT br.book_id, b.title, br.borrow_date, br.due_date " +
                "FROM borrow_records br " +
                "JOIN books b ON br.book_id = b.book_id " +
                "WHERE br.user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Borrow borrow = new Borrow(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getDate("borrow_date"),
                        resultSet.getDate("due_date")
                );
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
}