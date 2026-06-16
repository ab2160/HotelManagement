
package com.mycompany.model;


public class CurrentUser {
    private static String loggedInUsername;
    
    // Store the username when guest logs in
    public static void setUsername(String username) {
        loggedInUsername = username;
    }
    
    // Get the current logged-in username
    public static String getUsername() {
        return loggedInUsername;
    }
}
