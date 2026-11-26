package org.champlain.prog2.grandviewmovietheater.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
    private LocalTime aStartTime;
    /**
     * Saves the screening's end time. It cannot be null or after midnight during the week/2AM during the weekend.
     */
    private LocalTime aEndTime;
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
        this.setStartTime(pStartTime);
        this.setEndTime(pStartTime);
        this.setScreeningRoom(pScreeningRoom);
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link #aMovie}.
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
     * Setter method to validate the parameter's value and assign it to {@link #aScreeningDate}.
     *
     * @param pScreeningDate ScreeningDate object for the date.
     * @throws IllegalArgumentException If the ScreeningDate object is null
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
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
            isWeekendScreening = true;

        return isWeekendScreening;
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link #aStartTime}.
     *
     * @param pStartTime LocalTime value of the start time.
     * @throws IllegalArgumentException If the screening start time is null.
     * @throws IllegalArgumentException If the screening begins before 10AM during the weekend.
     * @throws IllegalArgumentException If the screening begins before 3PM during the week.
     */
    private void setStartTime(LocalTime pStartTime) {
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

        this.aStartTime = pStartTime;
    }

    /**
     * Getter method to get the start time.
     *
     * @return The start time as a LocalTime object.
     */
    public LocalTime getStartTime() {
        return this.aStartTime;
    }

    /**
     * Setter method for the end time.
     * <p>
     * The method takes the {@link #aStartTime} value and adds to it the {@link #aMovie} length
     * + 10 minutes (time to prepare for the next screening). Then, assigns the result to {@link #aEndTime}.
     * </p>
     *
     * @param pStartTime LocalTime value of the end time.
     * @throws IllegalArgumentException If the start time is null.
     * @throws IllegalArgumentException If the screening ends after midnight during the week.
     * @throws IllegalArgumentException If the screening ends after 2AM during the weekend.
     * @throws IllegalArgumentException If the screening ends before the screening begins during the weekend (10AM).
     * @throws IllegalArgumentException If the screening ends before the screening begins during the week (3PM).
     */
    private void setEndTime(LocalTime pStartTime) {
        if (pStartTime == null) {
            throw new IllegalArgumentException("The screening start time cannot be null.");
        }

        int movieLength = aMovie.getMovieLength();

        this.aEndTime = pStartTime.plusMinutes(movieLength + 10);

        // Check whether the end time passes midnight (so, on the next day).
        if (aEndTime.isBefore(pStartTime)) {
            // Get the previous day. First by calling getScreeningDate() of the Screening class, then of the ScreeningDate class.
            DayOfWeek day = getScreeningDate().getScreeningDate().getDayOfWeek();

            // Check if the previous day was during the weekend.
            boolean isWeekendScreening = day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;

            // If it was during the week, throw an error.
            if (!isWeekendScreening) {
                throw new IllegalArgumentException("Week screenings cannot end after midnight.");
            }

            // Otherwise, throw an error if the end time is after 2AM.
            if (aEndTime.isAfter(WEEKEND_END)) {
                throw new IllegalArgumentException("Weekend screenings cannot end after 2AM.");
            }
        // If not, check if the end time is before the screenings begin.
        } else {
            if (isWeekend()) {
                if (aEndTime.isBefore(WEEKEND_START))
                    throw new IllegalArgumentException("Weekend screenings begin at 10AM, they cannot end before then!");
            } else {
                if (aEndTime.isBefore(WEEK_START))
                    throw new IllegalArgumentException("Week screenings begin at 3PM, they cannot end before then!");
            }
        }
    }

    /**
     * Getter method to get the end time.
     *
     * @return The end time as a LocalTime object.
     */
    public LocalTime getEndTime() {
        return this.aEndTime;
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link #aScreeningRoom}.
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