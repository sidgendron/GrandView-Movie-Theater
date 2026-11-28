package org.champlain.prog2.grandviewmovietheater.Model;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Determines adult and child ticket prices based on the screening day.
 *
 * @author Keren
 */
public class TicketType {

    /**
     * The percentage of the adult ticket price charged for children.
     */
    private static final double CHILD_ADMISSION = 0.6;

    /**
     * The price of a child ticket.
     */
    private double aChildTicket;

    /**
     * The price of an adult ticket.
     */
    private double aAdultTicket;

    /**
     * Creates ticket prices based on the screening date and time.
     *
     * @param pScreeningDateTime the date and time of the screening
     * @throws IllegalArgumentException if the screening date is null
     */
    public TicketType(LocalDateTime pScreeningDateTime) {
        if (pScreeningDateTime == null) {
            throw new IllegalArgumentException(
                    "The screening day cannot be null.");
        }

        DayOfWeek screeningDay = pScreeningDateTime.getDayOfWeek();

        // Apply weekend or weekday ticket prices.
        if (screeningDay == DayOfWeek.SATURDAY
                || screeningDay == DayOfWeek.SUNDAY) {
            weekendAdmission();
        } else {
            weekAdmission();
        }
    }

    /**
     * Sets the ticket prices for weekend screenings.
     */
    private void weekendAdmission() {
        this.aAdultTicket = 15.00;
        this.aChildTicket = this.aAdultTicket * CHILD_ADMISSION;
    }

    /**
     * Sets the ticket prices for weekday screenings.
     */
    private void weekAdmission() {
        this.aAdultTicket = 10.00;
        this.aChildTicket = this.aAdultTicket * CHILD_ADMISSION;
    }

    /**
     * Gets the adult ticket price.
     *
     * @return the adult ticket price
     */
    public double getAdultTicket() {
        return this.aAdultTicket;
    }

    /**
     * Gets the child ticket price.
     *
     * @return the child ticket price
     */
    public double getChildTicket() {
        return this.aChildTicket;
    }
}

