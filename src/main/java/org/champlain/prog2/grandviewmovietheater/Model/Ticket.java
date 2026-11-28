package org.champlain.prog2.grandviewmovietheater.Model;

import java.time.LocalDateTime;

public class Ticket {
    private final LocalDateTime aPurchaseDate;
    private TicketType aTicketType;
    private Customer aCustomer;
    private Screening aScreening;

    public Ticket(TicketType pTicketType, Customer pCustomer, Screening pScreening) {
        this.setTicketType(pTicketType);
        this.setCustomer(pCustomer);
        this.setScreening(pScreening);
        this.aPurchaseDate = LocalDateTime.now();
    }

    private void setTicketType(TicketType pTicketType) {
        if (pTicketType == null) {
            throw new IllegalArgumentException("The ticket type cannot be null.");
        }

        this.aTicketType = pTicketType;
    }

    public TicketType getTicketType() {
        return this.aTicketType;
    }

    private void setCustomer(Customer pCustomer) {
        if (pCustomer == null) {
            throw new IllegalArgumentException("The customer cannot be null.");
        }

        this.aCustomer = pCustomer;
    }

    public Customer getCustomer() {
        return this.aCustomer;
    }

    private void setScreening(Screening pScreening) {
        if (pScreening == null) {
            throw new IllegalArgumentException("The screening cannot be null.");
        }

        this.aScreening = pScreening;
    }

    public Screening getScreening() {
        return this.aScreening;
    }

    public LocalDateTime getPurchaseDate() {
        return this.aPurchaseDate;
    }
}
