package org.champlain.prog2.grandviewmovietheater.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents a screening, which has a movie, a date, a start and end time, as well as a screening room.
 *
 * @author Sydney Gendron
 */
public class Screening {
    /**
     * Constant for the time at which screenings begin on weekdays (3PM).
     */
    private static final LocalTime WEEK_START = LocalTime.of(15, 0);
    /**
     * Constant for the time at which screenings end on weekdays (midnight).
     */
    private static final LocalTime WEEK_END = LocalTime.MIDNIGHT;
    /**
     * Constant for the time at which screenings begin on weekends (10AM).
     */
    private static final LocalTime WEEKEND_START = LocalTime.of(10, 0);
    /**
     * Constant for the time at which screenings end on weekends (2AM).
     */
    private static final LocalTime WEEKEND_END = LocalTime.of(2, 0);

    /**
     * Saves the screening's assigned movie. It cannot be null.
     */
    private Movie aMovie;
    /**
     * Saves the screening's date. It cannot be null.
     */
    private ScreeningDate aScreeningDate;
    /**
     * Saves the screening's start time. It cannot be null or before 3PM during the week/10AM during the weekend.
     */
    private LocalDateTime aStartDateTime;
    /**
     * Saves the screening's end time. It cannot be null or after midnight during the week/2AM during the weekend.
     */
    private LocalDateTime aEndDateTime;
    /**
     * Saves the screening's assigned screening room. It cannot be null.
     */
    private ScreeningRoom aScreeningRoom;

    /**
     * Constructor to create a new instance of Screening.
     * It calls each parameter's respective setter method to assign the object's attributes, as well
     * as the end time's setter method to assign it a value.
     *
     * @param pMovie Movie object for the assigned movie.
     * @param pScreeningDate ScreeningDate object for the date.
     * @param pStartTime LocalTime value of the start time.
     * @param pScreeningRoom ScreeningRoom object for the assigned screening room.
     */
    public Screening(Movie pMovie, ScreeningDate pScreeningDate, LocalTime pStartTime, ScreeningRoom pScreeningRoom) {
        this.setMovie(pMovie);
        this.setScreeningDate(pScreeningDate);
        this.setStartDateTime(pStartTime);
        this.setEndDateTime();
        this.setScreeningRoom(pScreeningRoom);
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link Screening#aMovie}.
     *
     * @param pMovie Movie object for the assigned movie.
     * @throws IllegalArgumentException If the Movie object is null.
     */
    private void setMovie(Movie pMovie) {
        if (pMovie == null) {
            throw new IllegalArgumentException("The movie cannot be null.");
        }

        this.aMovie = pMovie;
    }

    /**
     * Getter method to get the assigned movie.
     *
     * @return The movie as a Movie object.
     */
    public Movie getMovie() {
        return this.aMovie;
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link Screening#aScreeningDate}.
     *
     * @param pScreeningDate ScreeningDate object for the date.
     * @throws IllegalArgumentException If the ScreeningDate object is null.
     * @throws IllegalArgumentException If the screening date is before the current date.
     */
    private void setScreeningDate(ScreeningDate pScreeningDate) {
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
     * Getter method to get the date.
     *
     * @return The date as a ScreeningDate object.
     */
    public ScreeningDate getScreeningDate() {
        return this.aScreeningDate;
    }

    /**
     * Check if the screening date is during the weekend.
     *
     * @return True if the date is during the weekend, false otherwise.
     */
    private boolean isWeekend() {
        boolean isWeekendScreening = false;

        DayOfWeek day = aScreeningDate.getScreeningDate().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            isWeekendScreening = true;
        }

        return isWeekendScreening;
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link Screening#aStartDateTime}.
     *
     * @param pStartTime LocalTime value of the start time.
     * @throws IllegalArgumentException If the screening start time is null.
     * @throws IllegalArgumentException If the screening begins before 10AM during the weekend.
     * @throws IllegalArgumentException If the screening begins before 3PM during the week.
     */
    private void setStartDateTime(LocalTime pStartTime) {
        if (pStartTime == null) {
            throw new IllegalArgumentException("The screening start time cannot be null.");
        }

        if (isWeekend()) {
            if (pStartTime.isBefore(WEEKEND_START)) {
                throw new IllegalArgumentException("Weekend screenings cannot begin before 10AM.");
            }
        } else {
            if (pStartTime.isBefore(WEEK_START)) {
                throw new IllegalArgumentException("Week screenings cannot begin before 3PM.");
            }
        }

        this.aStartDateTime = LocalDateTime.of(aScreeningDate.getScreeningDate(), pStartTime);
    }

    /**
     * Getter method to get the start time.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getStartDateTime() {
        return this.aStartDateTime;
    }

    /**
     * Setter method for the end time.
     * <p>
     * The method takes the {@link Screening#aStartDateTime} value and adds to it the {@link Screening#aMovie} length
     * + 10 minutes (time to prepare for the next screening). Then, assigns the result to {@link Screening#aEndDateTime}.
     * </p>
     *
     * @throws IllegalArgumentException If the screening ends after 2AM during the weekend.
     * @throws IllegalArgumentException If the screening ends after midnight during the week.
     */
    private void setEndDateTime() {
        int movieLength = this.aMovie.getMovieLength();

        this.aEndDateTime = this.aStartDateTime.plusMinutes(movieLength + 10);

        LocalDate screeningDate = aScreeningDate.getScreeningDate();
        LocalDateTime weekendEndTime = screeningDate.plusDays(1).atTime(WEEKEND_END);
        LocalDateTime weekEndTime = screeningDate.plusDays(1).atTime(WEEK_END);

        if (isWeekend()) {
            if (aEndDateTime.isAfter(weekendEndTime)) {
                throw new IllegalArgumentException("Weekend screenings cannot end after 2AM.");
            }
        } else {
            if (aEndDateTime.isAfter(weekEndTime)) {
                throw new IllegalArgumentException("Week screenings cannot end after midnight.");
            }
        }
    }

    /**
     * Getter method to get the end time.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getEndDateTime() {
        return this.aEndDateTime;
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link Screening#aScreeningRoom}.
     *
     * @param pScreeningRoom ScreeningRoom object for the assigned screening room.
     * @throws IllegalArgumentException If the ScreeningRoom object is null.
     */
    private void setScreeningRoom(ScreeningRoom pScreeningRoom) {
        if (pScreeningRoom == null) {
            throw new IllegalArgumentException("The screening room cannot be null.");
        }

        this.aScreeningRoom = pScreeningRoom;
    }

    /**
     * Getter method to get the screening room.
     *
     * @return The screening room as a ScreeningRoom object.
     */
    public ScreeningRoom getScreeningRoom() {
        return this.aScreeningRoom;
    }
}