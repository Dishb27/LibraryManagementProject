package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.Getter;

public class DBConnection {
    private static DBConnection instance;
    @Getter
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/booklib";
    private final String username = "root";
    private final String password = "1234";

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
