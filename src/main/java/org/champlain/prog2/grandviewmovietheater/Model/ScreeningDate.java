package org.champlain.prog2.grandviewmovietheater.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScreeningDate {
    private LocalDate aScreeningDate;
    private final ObservableList<Screening> aScreenings;

    public ScreeningDate(LocalDate pScreeningDate) {
        this.setScreeningDate(pScreeningDate);
        this.aScreenings = FXCollections.observableArrayList();
    }

    private void setScreeningDate(LocalDate pScreeningDate) {
        if (pScreeningDate == null) {
            throw new IllegalArgumentException("The screening date cannot be null.");
        }

        LocalDate currentDate = LocalDate.now();
        if (pScreeningDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("The screening date cannot be in the past.");
        }

        this.aScreeningDate = pScreeningDate;
    }

    public LocalDate getScreeningDate() {
        return this.aScreeningDate;
    }

    public boolean isBefore(LocalDate pCurrentDate) {
        if (pCurrentDate == null) {
            throw new IllegalArgumentException("The current date cannot be null.");
        }

        return aScreeningDate.isBefore(pCurrentDate);
    }

    public void addScreening(Screening pScreening) {
        if (pScreening == null) {
            throw new IllegalArgumentException("The screening cannot be null.");
        }

        if (this.aScreenings.contains(pScreening)) {
            throw new IllegalArgumentException("This screening already exists.");
        }

        for (Screening screening : this.aScreenings) {
            if (pScreening.getScreeningRoom() == screening.getScreeningRoom()) {
                if (pScreening.getStartDateTime().isBefore(screening.getEndDateTime()) &&
                    pScreening.getEndDateTime().isAfter(screening.getStartDateTime())) {
                    throw new IllegalArgumentException("There is already a screening at this time.");
                }
            }
        }

        this.aScreenings.add(pScreening);
    }

    public void removeScreening(Screening pScreening) {
        if (pScreening == null) {
            throw new IllegalArgumentException("The screening cannot be null.");
        }

        this.aScreenings.remove(pScreening);
    }

    public ObservableList<Screening> getScreenings() {
        return FXCollections.unmodifiableObservableList(this.aScreenings);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy");
        return aScreeningDate.format(formatter);
    }
}
