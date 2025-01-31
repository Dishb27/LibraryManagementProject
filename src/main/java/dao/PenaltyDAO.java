package dao;

import db.DBConnection;
import model.Penalty;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PenaltyDAO {
    private Connection connection;

    public PenaltyDAO() {
        connection = DBConnection.getInstance().getConnection();
    }

    public List<Penalty> getAllPenalties() {
        List<Penalty> penalties = new ArrayList<>();
        String query = "SELECT * FROM penalties";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Penalty penalty = new Penalty(
                        rs.getInt("penalty_id"),
                        rs.getInt("borrow_id"),
                        rs.getInt("days_overdue"),
                        rs.getString("fine")
                );
                penalties.add(penalty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return penalties;
    }
}