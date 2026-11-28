package org.champlain.prog2.grandviewmovietheater.Model;

import java.time.LocalDateTime;

/**
 * Represents a ticket, which has a ticket type, a customer, a screening, and a purchase date and time.
 *
 * @author Sydney Gendron
 */
public class Ticket {
    /**
     * Holds the purchase date and time (when the ticket is created).
     */
    private final LocalDateTime aPurchaseDateAndTime;
    /**
     * Saves the ticket type. It cannot be null.
     */
    private TicketType aTicketType;
    /**
     * Saves the customer who purchased the ticket. It cannot be null.
     */
    private Customer aCustomer;
    /**
     * Saves the ticket's assigned screening. It cannot be null.
     */
    private Screening aScreening;

    /**
     * Constructor to create a new instance of Ticket.
     * <p>
     *      It calls each parameter's respective setter method to assign the object's attributes.
     *      It also initializes the {@link Ticket#aPurchaseDateAndTime}.
     * </p>
     *
     * @param pTicketType TicketType value of the ticket type.
     * @param pCustomer Customer value of the customer.
     * @param pScreening Screening value of the screening.
     */
    public Ticket(TicketType pTicketType, Customer pCustomer, Screening pScreening) {
        this.setTicketType(pTicketType);
        this.setCustomer(pCustomer);
        this.setScreening(pScreening);
        this.aPurchaseDateAndTime = LocalDateTime.now();
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link Ticket#aTicketType}.
     *
     * @param pTicketType TicketType object for the ticket type.
     * @throws IllegalArgumentException If the ticket type is null.
     */
    private void setTicketType(TicketType pTicketType) {
        if (pTicketType == null) {
            throw new IllegalArgumentException("The ticket type cannot be null.");
        }

        this.aTicketType = pTicketType;
    }

    /**
     * Getter method for the ticket type.
     *
     * @return The ticket type as a TicketType object.
     */
    public TicketType getTicketType() {
        return this.aTicketType;
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link Ticket#aCustomer}.
     *
     * @param pCustomer Customer object for the customer.
     * @throws IllegalArgumentException If the customer is null.
     */
    private void setCustomer(Customer pCustomer) {
        if (pCustomer == null) {
            throw new IllegalArgumentException("The customer cannot be null.");
        }

        this.aCustomer = pCustomer;
    }

    /**
     * Getter method for the customer.
     *
     * @return The customer as a Customer object.
     */
    public Customer getCustomer() {
        return this.aCustomer;
    }

    /**
     * Setter method to validate the parameter's value and assign it to {@link Ticket#aScreening}.
     *
     * @param pScreening Screening object for the screening.
     * @throws IllegalArgumentException If the screening is null.
     */
    private void setScreening(Screening pScreening) {
        if (pScreening == null) {
            throw new IllegalArgumentException("The screening cannot be null.");
        }

        this.aScreening = pScreening;
    }

    /**
     * Getter method for the screening.
     *
     * @return The screening as a Screening object.
     */
    public Screening getScreening() {
        return this.aScreening;
    }

    /**
     * Getter method for the purchase date and time.
     *
     * @return The purchase date and time as a LocalDateTime object.
     */
    public LocalDateTime getPurchaseDateAndTime() {
        return this.aPurchaseDateAndTime;
    }
}
