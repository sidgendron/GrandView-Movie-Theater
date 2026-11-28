package org.champlain.prog2.grandviewmovietheater.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a screening date.
 *
 * @author Sydney Gendron
 */
public class ScreeningDate {
    /**
     * Saves the screening date. It cannot be null or be in the past.
     */
    private LocalDate aScreeningDate;
    /**
     * Saves the collection of screening times for a date. Screenings cannot overlap.
     */
    private final ObservableList<Screening> aScreenings;

    /**
     * Constructor to create a new instance of ScreeningDate.
     * <p>
     *     It calls the screening date's setter method to assign it a value, and it creates
     *     the observable array list.
     * </p>
     *
     * @param pScreeningDate LocalDate value of the screening date.
     */
    public ScreeningDate(LocalDate pScreeningDate) {
        this.setScreeningDate(pScreeningDate);
        this.aScreenings = FXCollections.observableArrayList();
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link ScreeningDate#aScreeningDate}.
     *
     * @param pScreeningDate LocalDate object for the screening date.
     * @throws IllegalArgumentException If the screening date is null.
     * @throws IllegalArgumentException If the screening date is in the past.
     */
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

    /**
     * Getter method for the screening date.
     *
     * @return The screening date as a LocalDate object.
     */
    public LocalDate getScreeningDate() {
        return this.aScreeningDate;
    }

    /**
     * Check if the screening date is in the past.
     *
     * @param pCurrentDate LocalDate object for the current date.
     * @throws IllegalArgumentException If the current date is null.
     * @return True if the screening date is in the past, false otherwise.
     */
    public boolean isBefore(LocalDate pCurrentDate) {
        if (pCurrentDate == null) {
            throw new IllegalArgumentException("The current date cannot be null.");
        }

        return aScreeningDate.isBefore(pCurrentDate);
    }

    /**
     * Add a screening to the list of screenings for a given date.
     *
     * @param pScreening Screening object for the screening to add.
     * @throws IllegalArgumentException If the screening is null.
     * @throws IllegalArgumentException If the screening already exists.
     * @throws IllegalArgumentException If the screening is at the same time as another screening (overlap).
     */
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

    /**
     * Remove a screening from the list of screenings for a given date.
     *
     * @param pScreening Screening object for the screening to remove.
     * @throws IllegalArgumentException If the screening is null.
     */
    public void removeScreening(Screening pScreening) {
        if (pScreening == null) {
            throw new IllegalArgumentException("The screening cannot be null.");
        }

        this.aScreenings.remove(pScreening);
    }

    /**
     * The list of screenings for a given date.
     *
     * @return The list of screenings as an unmodifiable list.
     */
    public ObservableList<Screening> getScreenings() {
        return FXCollections.unmodifiableObservableList(this.aScreenings);
    }

    /**
     * The formatted version of the screening date.
     * 
     * @return The String representation of the formatted version of the screening date.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy");
        return aScreeningDate.format(formatter);
    }
}
