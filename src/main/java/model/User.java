package model;

import javafx.beans.property.*;
import java.sql.Date;

public class User {

    private final IntegerProperty userId;
    private final StringProperty name;
    private final StringProperty contact;
    private final ObjectProperty<Date> membershipDate;
    private final StringProperty role;
    private final StringProperty status;
    private final StringProperty password;  // Add this line for password


    public User(int userId, String name, String contact, Date membershipDate, String role, String status) {
        this.userId = new SimpleIntegerProperty(userId);
        this.name = new SimpleStringProperty(name);
        this.contact = new SimpleStringProperty(contact);
        this.membershipDate = new SimpleObjectProperty<>(membershipDate);
        this.role = new SimpleStringProperty(role);
        this.status = new SimpleStringProperty(status);
        this.password = new SimpleStringProperty(""); // Password is not used in this constructor, so default it to empty

    }

    public User(int userId, String name, String password, String role) {
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);  // Initialize password
        this.role = new SimpleStringProperty(role);
        this.contact = new SimpleStringProperty("");  // Default to empty, as it's not used for login
        this.status = new SimpleStringProperty("");   // Default to empty, as it's not used for login
        this.membershipDate = new SimpleObjectProperty<>(null); // Default to null, as it's not used for login
        this.userId = new SimpleIntegerProperty(userId); // Default to 0, as it's not used for login
    }


    // Properties for binding with TableView
    public IntegerProperty userIdProperty() {
        return userId;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public ObjectProperty<Date> membershipDateProperty() {
        return membershipDate;
    }

    public StringProperty roleProperty() {
        return role;
    }

    public StringProperty statusProperty() {
        return status;
    }

    // Standard getters and setters
    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public Date getMembershipDate() {
        return membershipDate.get();
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate.set(membershipDate);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    // Add getter for password
    public String getPassword() {
        return password.get();
    }

    // Add setter for password
    public void setPassword(String password) {
        this.password.set(password);
    }
}
