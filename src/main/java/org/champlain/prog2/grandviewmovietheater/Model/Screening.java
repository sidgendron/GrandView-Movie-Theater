package org.champlain.prog2.grandviewmovietheater.Model;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Screening {
    private static final LocalTime WEEK_START = LocalTime.of(15, 0);
    private static final LocalTime WEEK_END = LocalTime.MIDNIGHT;
    private static final LocalTime WEEKEND_START = LocalTime.of(10, 0);
    private static final LocalTime WEEKEND_END = LocalTime.of(2, 0);

    private Movie aMovie;
    private ScreeningDate aScreeningDate;
    private LocalTime aStartTime;
    private LocalTime aEndTime;
    private ScreeningRoom aScreeningRoom;

    public Screening(Movie pMovie, ScreeningDate pScreeningDate, LocalTime pStartTime, ScreeningRoom pScreeningRoom) {
        this.setMovie(pMovie);
        this.setScreeningDate(pScreeningDate);
        this.setStartTime(pStartTime);
        this.setEndTime(pStartTime);
        this.setScreeningRoom(pScreeningRoom);
    }

    private void setMovie(Movie pMovie) {
        if (pMovie == null) {
            throw new IllegalArgumentException("The movie cannot be null.");
        }

        this.aMovie = pMovie;
    }

    public String getMovie() {
        return this.aMovie.getMovieTitle();
    }

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

    public ScreeningDate getScreeningDate() {
        return this.aScreeningDate;
    }

    private boolean isWeekend() {
        boolean isWeekendScreening = false;

        DayOfWeek day = aScreeningDate.getScreeningDate().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
            isWeekendScreening = true;

        return isWeekendScreening;
    }

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

    public LocalTime getStartTime() {
        return this.aStartTime;
    }

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

    public LocalTime getEndTime() {
        return this.aEndTime;
    }

    private void setScreeningRoom(ScreeningRoom pScreeningRoom) {
        if (pScreeningRoom == null) {
            throw new IllegalArgumentException("The screening room cannot be null.");
        }

        this.aScreeningRoom = pScreeningRoom;
    }

    public ScreeningRoom getScreeningRoom() {
        return this.aScreeningRoom;
    }
}
