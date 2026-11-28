package org.champlain.prog2.grandviewmovietheater.Model;

import java.util.regex.Pattern;

/**
 * Represents a user of GVM.
 *
 * A user can be a customer or a manager.
 *
 * @author Keren
 */
public class User {

    /**
     * The unique ID assigned to the user.
     */
    private final int aUserId;

    /**
     * The username used to identify the user.
     */
    private String aUsername;

    /**
     * The user's email address.
     */
    private String aEmail;

    /**
     * The user's password.
     */
    private String aPassword;

    /**
     * Creates a user with the provided information.
     *
     * @param pUserId the user's unique ID
     * @param pUsername the user's username
     * @param pEmail the user's email address
     * @param pPassword the user's password
     * @throws IllegalArgumentException if the ID or any other value is invalid
     */
    public User(int pUserId, String pUsername,
                String pEmail, String pPassword) {

        if (pUserId <= 0) {
            throw new IllegalArgumentException(
                    "User ID must be greater than 0.");
        }

        this.aUserId = pUserId;
        this.setUsername(pUsername);
        this.setEmail(pEmail);
        this.setPassword(pPassword);
    }

    /**
     * Gets the user's unique ID.
     *
     * @return the user's ID
     */
    public int getUserId() {
        return this.aUserId;
    }

    /**
     * Gets the user's username.
     *
     * @return the user's username
     */
    public String getUsername() {
        return this.aUsername;
    }

    /**
     * Updates the user's username.
     *
     * @param pUsername the new username
     * @throws IllegalArgumentException if the username is null or blank
     */
    public void setUsername(String pUsername) {

        if (pUsername == null || pUsername.isBlank()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty.");
        }

        this.aUsername = pUsername;
    }

    /**
     * Gets the user's email address.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return this.aEmail;
    }

    /**
     * Updates the user's email address.
     *
     * @param pEmail the new email address
     * @throws IllegalArgumentException if the email address is invalid
     */
    public void setEmail(String pEmail) {

        // Check that the email is not null, blank or too long.
        if (pEmail == null || pEmail.isBlank() || pEmail.length() > 254) {
            throw new IllegalArgumentException(
                    "Email must contain between 1 and 254 characters.");
        }

        // Check that the email follows the required format.
        String emailRegex = "^[A-Za-z0-9]+([._%+-]?[A-Za-z0-9]+)*"
                + "@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$";

        Pattern emailPattern = Pattern.compile(emailRegex);

        if (!emailPattern.matcher(pEmail).matches()) {
            throw new IllegalArgumentException(
                    "Please enter a valid email address.");
        }

        this.aEmail = pEmail;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return this.aPassword;
    }

    /**
     * Updates the user's password.
     *
     * @param pPassword the new password
     * @throws IllegalArgumentException if the password is null or blank
     */
    public void setPassword(String pPassword) {

        if (pPassword == null || pPassword.isBlank()) {
            throw new IllegalArgumentException(
                    "Password cannot be empty.");
        }

        this.aPassword = pPassword;
    }
}