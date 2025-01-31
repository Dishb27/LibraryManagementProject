package controller;

import dao.*;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert; // Import Alert
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.List;



public class UserPage {

    @FXML
    private AnchorPane availableBooksPane;

    @FXML
    private AnchorPane borrowBooksPane;

    @FXML
    private HBox borrowandreturnhbox;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colBookTitle;

    @FXML
    private TableColumn<Borrow, Date> colBorrowedDateUser;

    @FXML
    private TableColumn<?, ?> colBurrowedDateReturnTable;

    @FXML
    private TableColumn<?, ?> colDueDateReturnTable;

    @FXML
    private TableColumn<Borrow, Date> colDueDateUser;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<Borrow, Integer> colbookIduser;

    @FXML
    private TableColumn<Borrow, String> colbookTitleUser;

    @FXML
    private TableColumn<?, ?> colbookidreturnTable;

    @FXML
    private TableColumn<?, ?> colborrowIdReturnTable;

    @FXML
    private JFXTextField fieldsearchbyBooktitle;

    @FXML
    private Label lblfineAmount;

    @FXML
    private AnchorPane myProfilePane;

    @FXML
    private AnchorPane mybooksPane;

    @FXML
    private TableView<Borrow> mybookstableUser;

    @FXML
    private AnchorPane payFInePane;

    @FXML
    private TableView<?> payFineTable;

    @FXML
    private AnchorPane returnBookPane;

    @FXML
    private HBox myProfilehboxPane;

    @FXML
    private TableView<?> returnbookTable;

    @FXML
    private TableView<?> tablebookavailabitiy;

    @FXML
    private JFXTextField txtBookIdborrowBook;

    @FXML
    private JFXTextField txtBorrowId;

    @FXML
    private JFXTextField txtnumdays;

    @FXML
    private TextField txtpaymountAmountFine;

    @FXML
    private JFXTextField txtuserContact;

    @FXML
    private JFXTextField txtuserId;

    @FXML
    private JFXTextField txtuserIdborrowBook;

    @FXML
    private JFXTextField txtuserName;

    @FXML
    private JFXTextField txtusermemDate;


    @FXML
    private HBox viewbooksUservbox;

    private BorrowDAO borrowDAO;

    public UserPage() {
        borrowDAO = new BorrowDAO();
    }

    @FXML
    public void initialize() {
        // Initialize the table columns
        colbookIduser.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colbookTitleUser.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colBorrowedDateUser.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        colDueDateUser.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }

    @FXML
    void ResetOnAction(ActionEvent event) {

    }

    @FXML
    void ReturnBooksOnAction(ActionEvent event) {
        clearallPanes();
        borrowandreturnhbox.setVisible(true);
        returnBookPane.setVisible(true);

    }

    @FXML
    void availableBooksOnActionUser(ActionEvent event) {
        clearallPanes();
        viewbooksUservbox.setVisible(true);
        availableBooksPane.setVisible(true);

    }

    @FXML
    void borrowBooksOnAction(ActionEvent event) {
        clearallPanes();
        borrowandreturnhbox.setVisible(true);
        borrowBooksPane.setVisible(true);


    }

    @FXML
    void borrowOnAction(ActionEvent event) {

    }

    @FXML
    void myBooksOnActionUser(ActionEvent event) {
        clearallPanes();
        viewbooksUservbox.setVisible(true);
        mybooksPane.setVisible(true);

        // Load borrowed books for the logged-in user
        int loggedInUserId = Integer.parseInt(txtuserId.getText()); // Assuming the logged-in user's ID is stored in txtuserId
        loadBorrowedBooks(loggedInUserId);

    }

    private void loadBorrowedBooks(int userId) {
        // Fetch borrowed books for the user
        List<Borrow> borrowedBooks = borrowDAO.getBorrowedBooksByUserId(userId);
        // Debug: Print borrowed books
        for (Borrow borrow : borrowedBooks) {
            System.out.println("Book ID: " + borrow.getBookId() +
                    ", Due Date: " + borrow.getDueDate() +
                    ", Borrowed Date: " + borrow.getBorrowedDate());
        }

        // Convert the list to an observable list
        ObservableList<Borrow> borrowedBooksList = FXCollections.observableArrayList(borrowedBooks);

        // Set the items in the table
        mybookstableUser.setItems(borrowedBooksList);
        System.out.println("Items in table: " + mybookstableUser.getItems());

    }

    @FXML
    void payFineOnAction(ActionEvent event) {
        clearallPanes();
        borrowandreturnhbox.setVisible(true);
        payFInePane.setVisible(true);

    }

    @FXML
    void returnOnAction(ActionEvent event) {

    }

    @FXML
    void showBorrowAndReturnButtons(ActionEvent event) {
        clearallPanes();
        borrowandreturnhbox.setVisible(true);
        borrowBooksPane.setVisible(true);

    }

    @FXML
    void showViewMyProfileButtons(ActionEvent event) {
        clearallPanes();
        myProfilehboxPane.setVisible(true);
        myProfilePane.setVisible(true);
    }

    @FXML
    void showMyProfileOnAction(ActionEvent event){
        clearallPanes();
        myProfilehboxPane.setVisible(true);
        myProfilePane.setVisible(true);
    }


    @FXML
    void showViewUserBooksButtons(ActionEvent event) {
        clearallPanes();
        viewbooksUservbox.setVisible(true);
        mybooksPane.setVisible(true);
    }

    public void setUserDetails(int userId) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserDetails(userId); // Fetch user details using user_id

        if (user != null) {
            // Populate the fields with the user's details
            txtuserId.setText(String.valueOf(user.getUserId()));
            txtuserName.setText(user.getName());
            txtuserContact.setText(user.getContact());
            txtusermemDate.setText(user.getMembershipDate().toString());
        } else {
            showAlert("Error", "Failed to retrieve user details.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearallPanes() {
        availableBooksPane.setVisible(false);
        borrowBooksPane.setVisible(false);
        myProfilePane.setVisible(false);
        mybooksPane.setVisible(false);
        payFInePane.setVisible(false);
        returnBookPane.setVisible(false);
        myProfilehboxPane.setVisible(false);
        borrowandreturnhbox.setVisible(false);
        viewbooksUservbox.setVisible(false);
    }




}

