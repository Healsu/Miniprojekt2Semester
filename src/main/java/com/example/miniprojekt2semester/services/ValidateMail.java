package com.example.miniprojekt2semester.services;

//Jimmy kode
public class ValidateMail {

    public boolean isEmailValid(String email) {

        int atIndexCounter = 0;
        int dotIndexCounter = 0;

        // check for if string contains what we are looking for
        boolean checkingForAt = email.contains("@");
        boolean checkingForDot = email.contains(".");

        if (checkingForAt && checkingForDot ) {
            for (int i = 0; i < email.length(); i++) {

                // Replace the placeholder viable with the current index for the characters that we are seaching for
                if (checkingForAt){
                    atIndexCounter = email.indexOf("@");
                }
                if (checkingForDot){
                    dotIndexCounter = email.indexOf(".");
                }
            }

            // Check if the @ comes before the . in the email string
            // @ should be lower than . since it comes for before . in the string.
            if (atIndexCounter < dotIndexCounter){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
