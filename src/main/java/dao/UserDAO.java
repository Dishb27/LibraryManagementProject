package dao;

import db.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        connection = DBConnection.getInstance().getConnection();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                Date membershipDate = rs.getDate("membership_date");
                String role = rs.getString("role");
                String status = rs.getString("status");

                User user = new User(userId, name, contact, membershipDate, role, status);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserForLogin(String username) {
        User user = null;
        String query = "SELECT user_id, name, password, role FROM users WHERE name = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);  // Use the username to find the user

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id"); // Retrieve user_id
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    user = new User(userId, name, password, role);  // Create User with only necessary fields for login
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserDetails(int userId) {
        System.out.println("Fetching details for user ID: " + userId); // Debugging

        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String contact = rs.getString("contact");
                    Date membershipDate = rs.getDate("membership_date");
                    String role = rs.getString("role");
                    String status = rs.getString("status");

                    user = new User(userId, name, contact, membershipDate, role, status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public void updateUser(User user) {
        String query = "UPDATE users SET name = ?, contact = ?, membership_date = ?, role = ?, status = ? WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getContact());
            ps.setDate(3, user.getMembershipDate());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getStatus());
            ps.setInt(6, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        String query = "INSERT INTO users (name, contact, membership_date, role, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getContact());
            ps.setDate(3, user.getMembershipDate());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
