package org.champlain.prog2.grandviewmovietheater.Model;

/**
 * Represents a customer of the GMV.
 *
 * A customer inherits the common user information
 * and has an additional phone number.
 *
 * @author Keren
 */
public class Customer extends User {

    /**
     * The customer's phone number.
     */
    private String aPhoneNumber;

    /**
     * Creates a customer with the provided information.
     *
     * @param pUserId the customer's unique ID
     * @param pUsername the customer's username
     * @param pEmail the customer's email address
     * @param pPassword the customer's password
     * @param pPhoneNumber the customer's phone number
     * @throws IllegalArgumentException if the phone number is invalid
     */
    public Customer(int pUserId, String pUsername,
                    String pEmail, String pPassword,
                    String pPhoneNumber) {

        // Initialize the inherited User attributes.
        super(pUserId, pUsername, pEmail, pPassword);

        this.setPhoneNumber(pPhoneNumber);
    }

    /**
     * Gets the customer's phone number.
     *
     * @return the customer's phone number
     */
    public String getPhoneNumber() {
        return this.aPhoneNumber;
    }

    /**
     * Updates the customer's phone number.
     *
     * @param pPhoneNumber the new phone number
     * @throws IllegalArgumentException if the phone number is invalid
     */
    public void setPhoneNumber(String pPhoneNumber) {

        // Remove spaces and common formatting characters before validation.
        if (pPhoneNumber == null) {
            throw new IllegalArgumentException(
                    "Phone number cannot be null.");
        }

        String formattedNumber = pPhoneNumber.replaceAll("[\\s()-]", "");

        // Accept 10 digits or 11 digits beginning with 1.
        if (!formattedNumber.matches("1?[0-9]{10}")) {
            throw new IllegalArgumentException(
                    "Please enter a valid phone number.");
        }

        this.aPhoneNumber = pPhoneNumber;
    }
}

