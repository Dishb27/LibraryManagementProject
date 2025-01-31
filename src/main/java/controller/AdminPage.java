package controller;
import dao.*;
import model.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.converter.DateStringConverter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Date; // Add this import
import util.SqlDateStringConverter; // Import the custom converter
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminPage {
    @FXML
    private TableView<Book> bookManViewTable;

    @FXML
    private TableView<User> userManViewTable;

    @FXML
    private TableView<Penalty> tablepenalty;


    @FXML
    private TableColumn<User, String> ContactViewUsersAdmin;

    @FXML
    private AnchorPane addBooksPane;

    @FXML
    private AnchorPane addUsersPane;

    @FXML
    private HBox bookManagebox;

    @FXML
    private  HBox userManagebox;

    @FXML
    private HBox fineManageHbox;

    @FXML
    private VBox bookOptionBoxAdmin;

    @FXML
    private TableColumn<Book, String> colAuthorAdmin;

    @FXML
    private TableColumn<Book, Integer> colBookIDAdmin;

    @FXML
    private TableColumn<Book, String> colBookTitleAdmin;

    @FXML
    private TableColumn<Book, String> colGenreAdmin;

    @FXML
    private TableColumn<Book, String> colIsbnAdmin;

    @FXML
    private TableColumn<Book, Integer> colQuantityAdmin;

    @FXML
    private TableColumn<?, ?> colborrowIdFineAdmin;

    @FXML
    private TableColumn<?, ?> coldaysOverdieFineAdmin;

    @FXML
    private TableColumn<?, ?> colfineIdFineManaAdmin;

    @FXML
    private TableColumn<?, ?> colfinemanagementAdmin;

    @FXML
    private TableColumn<User, Date> memDateViewUsersAdmin;

    @FXML
    private DatePicker membershipDateAddUserAdmin;

    @FXML
    private TableColumn<User, String> nameViewUsersAdmin;

    @FXML
    private AnchorPane penaltyPane;

    @FXML
    private AnchorPane reportsPane;

    @FXML
    private ChoiceBox<String> roleAddUserAdmin;

    @FXML
    private TableColumn<User, String> roleViewUsersAdmin;

    @FXML
    private ChoiceBox<String> statusAddUserAdmin;

    @FXML
    private TableColumn<User, String> statusViewUsersAdmin;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtContactAddUserAdmin;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtNameAddUserAdmin;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtbookTitle;

    @FXML
    private TextField txtsearchBarAdminViewBooks;

    @FXML
    private TableColumn<User, Integer> userIdViewUsersAdmin;

    @FXML
    private AnchorPane viewBooksPane;

    @FXML
    private AnchorPane viewUsersPane;

    private BookDAO bookDAO;
    private UserDAO userDAO;
    private PenaltyDAO penaltyDAO;

    private ObservableList<Book> bookList;
    private ObservableList<User> userList;
    private ObservableList<Penalty> penaltyList;



    public AdminPage() {
        bookDAO = new BookDAO();
        userDAO = new UserDAO();
        penaltyDAO =new PenaltyDAO();
    }

    @FXML
    public void initialize() {
        // Populate the Role ChoiceBox with options
        roleAddUserAdmin.getItems().addAll("Admin", "Member");
        roleAddUserAdmin.setValue("Admin");  // Optional: Set a default value, like "Admin"

        // Populate the Status ChoiceBox with options
        statusAddUserAdmin.getItems().addAll("Active", "Inactive");
        statusAddUserAdmin.setValue("Active");

        booksManage();
        userManage();
        penaltyManage();

    }

    @FXML
    public void penaltyManage(){
        // Initialize the penalty table columns
        colfineIdFineManaAdmin.setCellValueFactory(new PropertyValueFactory<>("penaltyId"));
        colborrowIdFineAdmin.setCellValueFactory(new PropertyValueFactory<>("borrowId"));
        coldaysOverdieFineAdmin.setCellValueFactory(new PropertyValueFactory<>("daysOverdue"));
        colfinemanagementAdmin.setCellValueFactory(new PropertyValueFactory<>("fine"));

        // Fetch penalties from the database
        penaltyList = FXCollections.observableArrayList(penaltyDAO.getAllPenalties());
        tablepenalty.setItems(penaltyList);
    }

    @FXML
    public void booksManage(){
        bookManViewTable.setEditable(true);

        colBookTitleAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        colAuthorAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        colGenreAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        colIsbnAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        colQuantityAdmin.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        colBookIDAdmin.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty().asObject());
        colBookTitleAdmin.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        colAuthorAdmin.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        colGenreAdmin.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        colIsbnAdmin.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        colQuantityAdmin.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        bookList = FXCollections.observableArrayList(bookDAO.getAllBooks());
        bookManViewTable.setItems(bookList);
        setupTableEditing();
        setupSearchFilter();

    }

    @FXML
    public void userManage(){
        userManViewTable.setEditable(true);

        // Set cell factories for editing and displaying data
        userIdViewUsersAdmin.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nameViewUsersAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        ContactViewUsersAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        roleViewUsersAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        statusViewUsersAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        memDateViewUsersAdmin.setCellFactory(TextFieldTableCell.forTableColumn(new SqlDateStringConverter())); // Use custom converter

        // Set cell value factories to bind data properties
        userIdViewUsersAdmin.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
        nameViewUsersAdmin.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ContactViewUsersAdmin.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
        roleViewUsersAdmin.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        statusViewUsersAdmin.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        memDateViewUsersAdmin.setCellValueFactory(cellData -> cellData.getValue().membershipDateProperty());

        userList = FXCollections.observableArrayList(userDAO.getAllUsers());
        userManViewTable.setItems(userList);
        setupUserTableEditing();

    }


    @FXML
    private void setupTableEditing() {
        // Enable editing for each column
        colBookIDAdmin.setEditable(true);
        colBookTitleAdmin.setEditable(true);
        colAuthorAdmin.setEditable(true);
        colGenreAdmin.setEditable(true);
        colIsbnAdmin.setEditable(true);
        colQuantityAdmin.setEditable(true);

        // Set the onEditCommit event handlers for each column
        colBookIDAdmin.setOnEditCommit(this::handleBookIdEdit);
        colBookTitleAdmin.setOnEditCommit(this::handleBookTitleEdit);
        colAuthorAdmin.setOnEditCommit(this::handleAuthorEdit);
        colGenreAdmin.setOnEditCommit(this::handleGenreEdit);
        colIsbnAdmin.setOnEditCommit(this::handleIsbnEdit);
        colQuantityAdmin.setOnEditCommit(this::handleQuantityEdit);
    }

    @FXML
    private void handleBookIdEdit(TableColumn.CellEditEvent<Book, Integer> event) {
        Book book = event.getRowValue();
        book.setBookId(event.getNewValue());
        bookDAO.updateBook(book);
        refreshBookTable();
    }

    @FXML
    private void handleBookTitleEdit(TableColumn.CellEditEvent<Book, String> event) {
        Book book = event.getRowValue();
        book.setTitle(event.getNewValue());
        bookDAO.updateBook(book);
        refreshBookTable();
    }

    @FXML
    private void handleAuthorEdit(TableColumn.CellEditEvent<Book, String> event) {
        Book book = event.getRowValue();
        book.setAuthor(event.getNewValue());
        bookDAO.updateBook(book);
        refreshBookTable();
    }

    @FXML
    private void handleGenreEdit(TableColumn.CellEditEvent<Book, String> event) {
        Book book = event.getRowValue();
        book.setGenre(event.getNewValue());
        bookDAO.updateBook(book);
        refreshBookTable();
    }

    @FXML
    private void handleIsbnEdit(TableColumn.CellEditEvent<Book, String> event) {
        Book book = event.getRowValue();
        book.setIsbn(event.getNewValue());
        bookDAO.updateBook(book);
        refreshBookTable();
    }

    @FXML
    private void handleQuantityEdit(TableColumn.CellEditEvent<Book, Integer> event) {
        Book book = event.getRowValue();
        book.setQuantity(event.getNewValue());
        bookDAO.updateBook(book);
        refreshBookTable();
    }

    private void refreshBookTable() {
        bookList.setAll(bookDAO.getAllBooks());
    }

    @FXML
    private void adminViewBooksEdit(ActionEvent event) {
        if (!bookManViewTable.getItems().isEmpty()) {
            bookManViewTable.setEditable(true);

            // Get selected book
            Book selectedBook = bookManViewTable.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                // If no book is selected, select the first one
                bookManViewTable.getSelectionModel().selectFirst();
            }

            // Show alert to inform user that table is now editable
            showAlert(Alert.AlertType.INFORMATION, "Edit Mode",
                    "The table is now in edit mode. Double-click any cell to edit.");
        } else {
            showAlert(Alert.AlertType.WARNING, "No Books",
                    "There are no books to edit.");
        }
    }

    @FXML
    void adminViewBooksDelete(ActionEvent event) {
        Book selectedBook = bookManViewTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Confirm Delete");
            confirmDialog.setHeaderText("Delete Book");
            confirmDialog.setContentText("Are you sure you want to delete: " +
                    selectedBook.getTitle() + "?");

            confirmDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Delete from database
                    bookDAO.deleteBook(selectedBook.getBookId());

                    // Remove from TableView
                    bookList.remove(selectedBook);

                    showAlert(Alert.AlertType.INFORMATION, "Success",
                            "Book deleted successfully.");
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection",
                    "Please select a book to delete.");
        }
    }


    @FXML
    void addBookOnAction(ActionEvent event) {
        String title = txtbookTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String isbn = txtISBN.getText();
        String quantityStr = txtQuantity.getText();
        String availability = txtAvailability.getText();

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || isbn.isEmpty() || quantityStr.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill all the fields!");
        } else {
            try {
                int quantity = Integer.parseInt(quantityStr);
                // Create a new Book object
                Book book = new Book(0, title, author, genre, isbn, quantity,availability); // 0 for auto-generated bookId

                // Call the DAO method to add the book to the database
                BookDAO bookDAO = new BookDAO();
                bookDAO.addBook(book);

                // Refresh the list of books and update the table
                bookList.setAll(bookDAO.getAllBooks());
                bookManViewTable.setItems(bookList);

                // Show success message
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully!");

                // Reset the form
                resetAddBookForm();
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Quantity must be a number.");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding the book.");
            }
        }
    }

    @FXML
    void addUsersAdmin(ActionEvent event) {
        clearAllPanes();
        userManagebox.setVisible(true);
        addUsersPane.setVisible(true);
        viewUsersPane.setVisible(false);
    }

    @FXML
    void adduserAdminOnAction(ActionEvent event) {
        // Collect user details from input fields
        String name = txtNameAddUserAdmin.getText();
        String contact = txtContactAddUserAdmin.getText();
        String role = roleAddUserAdmin.getValue();
        String status = statusAddUserAdmin.getValue();
        Date membershipDate = Date.valueOf(membershipDateAddUserAdmin.getValue()); // Convert LocalDate to java.sql.Date

        // Validate input fields
        if (name.isEmpty() || contact.isEmpty() || role == null || status == null || membershipDate == null) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill all the fields!");
        } else {
            try {
                // Create a new User object
                User user = new User(0, name, contact, membershipDate, role, status); // 0 for auto-generated userId

                // Call the DAO method to add the user to the database
                userDAO.addUser(user);

                // Refresh the list of users and update the table
                userList.setAll(userDAO.getAllUsers());
                userManViewTable.setItems(userList);

                // Show success message
                showAlert(Alert.AlertType.INFORMATION, "Success", "User added successfully!");

                // Reset the form
                resetAddUserForm();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding the user.");
            }
        }
    }

    private void setupSearchFilter() {
        txtsearchBarAdminViewBooks.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                // Reset table to original book list when search bar is empty
                bookManViewTable.setItems(bookList);
            } else {
                // Filter book list based on title
                ObservableList<Book> filteredList = bookList.filtered(
                        book -> book.getTitle().toLowerCase().contains(newValue.toLowerCase())
                );
                bookManViewTable.setItems(filteredList);
            }
        });
    }


    @FXML
    private void editViewUsersAdmin(ActionEvent event) {
        if (!userManViewTable.getItems().isEmpty()) {
            userManViewTable.setEditable(true);

            // Get selected user
            User selectedUser = userManViewTable.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                // If no user is selected, select the first one
                userManViewTable.getSelectionModel().selectFirst();
            }

            // Show alert to inform user that table is now editable
            showAlert(Alert.AlertType.INFORMATION, "Edit Mode",
                    "The table is now in edit mode. Double-click any cell to edit.");
        } else {
            showAlert(Alert.AlertType.WARNING, "No Users",
                    "There are no users to edit.");
        }
    }

    @FXML
    private void deleteViewUsersAdmin(ActionEvent event) {
        User selectedUser = userManViewTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Confirm Delete");
            confirmDialog.setHeaderText("Delete User");
            confirmDialog.setContentText("Are you sure you want to delete: " +
                    selectedUser.getName() + "?");

            confirmDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Delete from database
                    userDAO.deleteUser(selectedUser.getUserId());

                    // Remove from TableView
                    userList.remove(selectedUser);

                    showAlert(Alert.AlertType.INFORMATION, "Success",
                            "User deleted successfully.");
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection",
                    "Please select a user to delete.");
        }
    }

    @FXML
    private void setupUserTableEditing() {
        // Enable editing for each column
        userIdViewUsersAdmin.setEditable(true);
        nameViewUsersAdmin.setEditable(true);
        ContactViewUsersAdmin.setEditable(true);
        roleViewUsersAdmin.setEditable(true);
        statusViewUsersAdmin.setEditable(true);
        memDateViewUsersAdmin.setEditable(true);

        // Set the onEditCommit event handlers for each column
        userIdViewUsersAdmin.setOnEditCommit(this::handleUserIdEdit);
        nameViewUsersAdmin.setOnEditCommit(this::handleNameEdit);
        ContactViewUsersAdmin.setOnEditCommit(this::handleContactEdit);
        roleViewUsersAdmin.setOnEditCommit(this::handleRoleEdit);
        statusViewUsersAdmin.setOnEditCommit(this::handleStatusEdit);
        memDateViewUsersAdmin.setOnEditCommit(this::handleMembershipDateEdit);
    }

    @FXML
    private void handleUserIdEdit(TableColumn.CellEditEvent<User, Integer> event) {
        User user = event.getRowValue();
        user.setUserId(event.getNewValue());
        userDAO.updateUser(user);
        refreshUserTable();
    }

    @FXML
    private void handleNameEdit(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setName(event.getNewValue());
        userDAO.updateUser(user);
        refreshUserTable();
    }

    @FXML
    private void handleContactEdit(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setContact(event.getNewValue());
        userDAO.updateUser(user);
        refreshUserTable();
    }

    @FXML
    private void handleRoleEdit(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setRole(event.getNewValue());
        userDAO.updateUser(user);
        refreshUserTable();
    }

    @FXML
    private void handleStatusEdit(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setStatus(event.getNewValue());
        userDAO.updateUser(user);
        refreshUserTable();
    }

    @FXML
    private void handleMembershipDateEdit(TableColumn.CellEditEvent<User, Date> event) {
        User user = event.getRowValue();
        user.setMembershipDate(event.getNewValue());
        userDAO.updateUser(user);
        refreshUserTable();
    }

    private void refreshUserTable() {
        userList.setAll(userDAO.getAllUsers());
    }




    @FXML
    void dowloadReports(ActionEvent event) {
        System.out.println("Download Reports action triggered.");
    }



    @FXML
    void logoutOnActionAdmin(ActionEvent event) {
        System.out.println("Admin logged out.");
    }

    @FXML
    void resetAddBookFormOnAction(ActionEvent event) {
        resetAddBookForm();
    }

    @FXML
    void resetadduserAdmin(ActionEvent event) {
        resetAddUserForm();
    }


    @FXML
    void showAddBooksPanel(ActionEvent event) {
        clearAllPanes();
        addBooksPane.setVisible(true);
        viewBooksPane.setVisible(false);
        bookManagebox.setVisible(true);
        resetAddBookForm();

    }

    @FXML
    void showBookManagementButtons(ActionEvent event) {
        clearAllPanes();
        bookManagebox.setVisible(true);
        addBooksPane.setVisible(true);
        resetAddBookForm();

    }

    @FXML
    void showFineManagementButtons(ActionEvent event) {
        clearAllPanes();
        fineManageHbox.setVisible(true);
        penaltyPane.setVisible(true);
    }

    @FXML
    void manageBorrowFineaction(ActionEvent event){
        clearAllPanes();
        fineManageHbox.setVisible(true);
        penaltyPane.setVisible(true);
    }

    @FXML
    void showReportTypes(ActionEvent event) {
        clearAllPanes();
        reportsPane.setVisible(true);
    }

    @FXML
    void showReportsButtons(ActionEvent event) {
        clearAllPanes();
        reportsPane.setVisible(true);
    }

    @FXML
    void showUserManagementButtons(ActionEvent event) {
        clearAllPanes();
        userManagebox.setVisible(true);
        addUsersPane.setVisible(true);
        viewUsersPane.setVisible(false);
    }

    @FXML
    void showViewBooksPanel(ActionEvent event) {
        clearAllPanes();
        viewBooksPane.setVisible(true);
        addBooksPane.setVisible(false);
        bookManagebox.setVisible(true);
    }

    @FXML
    void viewUsersAdmin(ActionEvent event) {
        clearAllPanes();
        userManagebox.setVisible(true);
        viewUsersPane.setVisible(true);
        addUsersPane.setVisible(false);
    }



    private void clearAllPanes() {
        addBooksPane.setVisible(false);
        addUsersPane.setVisible(false);
        penaltyPane.setVisible(false);
        reportsPane.setVisible(false);
        viewBooksPane.setVisible(false);
        viewUsersPane.setVisible(false);
        bookManagebox.setVisible(false);
        fineManageHbox.setVisible(false);
        userManagebox.setVisible(false);
    }

    private void resetAddBookForm() {
        txtbookTitle.clear();
        txtAuthor.clear();
        txtGenre.clear();
        txtISBN.clear();
        txtQuantity.clear();
        txtAvailability.clear();
    }

    private void resetAddUserForm() {
        txtNameAddUserAdmin.clear();
        txtContactAddUserAdmin.clear();
        membershipDateAddUserAdmin.setValue(null);
        roleAddUserAdmin.setValue(null);
        statusAddUserAdmin.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }



}
