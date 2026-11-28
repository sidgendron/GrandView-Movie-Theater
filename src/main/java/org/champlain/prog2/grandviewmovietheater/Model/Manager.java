package org.champlain.prog2.grandviewmovietheater.Model;

import java.util.regex.Pattern;

/**
 * Represents a manager, which has an ID, an email, and a password.
 *
 * @author Sydney Gendron
 */
public class Manager extends User {
    /**
     * Holds the manager.
     */
    private static Manager aManager;

    /**
     * Constructor that initializes the inherited {@link User} (class it extends) attributes.
     *
     * @param pUserID int value for the user ID.
     * @param pEmail String value of the email.
     * @param pPassword String value of the password.
     */
    private Manager(int pUserID, String pEmail, String pPassword) {
        super(pUserID, pEmail, pPassword);
    }

    /**
     * Returns the manager.
     * <p>
     *     In addition, if no Manager exists, it creates a Manager instance.
     * </p>
     *
     * @param pUserID int value for the user ID.
     * @param pEmail String object for the email.
     * @param pPassword String object for the password.
     * @throws IllegalArgumentException If the user ID is below zero.
     * @throws IllegalArgumentException If the email is null or blank.
     * @throws IllegalArgumentException If the email address has an incorrect format.
     * @throws IllegalArgumentException If the password is null or blank.
     * @return The manager as a Manager object.
     */
    public static Manager getManager(int pUserID, String pEmail, String pPassword) {
        if (aManager == null) {
            if (pUserID < 0) {
                throw new IllegalArgumentException("The user ID cannot be below zero.");
            }

            if (pEmail == null || pEmail.isBlank()) {
                throw new IllegalArgumentException("The email cannot be left blank.");
            }

            // The format of a proper email address.
            String emailRegex = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@" + "[\\p{L}0-9][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
            Pattern emailPattern = Pattern.compile(emailRegex);
            // Check if the manager's email address follows the format "local-part@domain".
            if (!emailPattern.matcher(pEmail).matches()) {
                throw new IllegalArgumentException("The email address must follow the format: local-part@domain.");
            }

            if (pPassword == null || pPassword.isBlank()) {
                throw new IllegalArgumentException("The password cannot be left blank.");
            }

            aManager = new Manager(pUserID, pEmail, pPassword);
        }

        return aManager;
    }
}
