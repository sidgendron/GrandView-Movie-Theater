package org.champlain.prog2.grandviewmovietheater.Model;

import java.util.regex.Pattern;

public class Manager extends User {
    private static Manager aManager;

    private Manager(int pUserID, String pEmail, String pPassword) {
        super(pUserID, pEmail, pPassword);
    }

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
