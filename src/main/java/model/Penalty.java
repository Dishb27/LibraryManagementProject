package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Penalty {
    private final IntegerProperty penaltyId;
    private final IntegerProperty borrowId;
    private final IntegerProperty daysOverdue;
    private final StringProperty fine;

    public Penalty(int penaltyId, int borrowId, int daysOverdue, String fine) {
        this.penaltyId = new SimpleIntegerProperty(penaltyId);
        this.borrowId = new SimpleIntegerProperty(borrowId);
        this.daysOverdue = new SimpleIntegerProperty(daysOverdue);
        this.fine = new SimpleStringProperty(fine);
    }

    public int getPenaltyId() {
        return penaltyId.get();
    }

    public IntegerProperty penaltyIdProperty() {
        return penaltyId;
    }

    public void setPenaltyId(int penaltyId) {
        this.penaltyId.set(penaltyId);
    }

    public int getBorrowId() {
        return borrowId.get();
    }

    public IntegerProperty borrowIdProperty() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId.set(borrowId);
    }

    public int getDaysOverdue() {
        return daysOverdue.get();
    }

    public IntegerProperty daysOverdueProperty() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue.set(daysOverdue);
    }

    public String getFine() {
        return fine.get();
    }

    public StringProperty fineProperty() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine.set(fine);
    }
}