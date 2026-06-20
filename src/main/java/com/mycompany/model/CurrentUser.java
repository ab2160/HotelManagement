
package com.mycompany.model;


public class CurrentUser {
    private static String loggedInUsername;
    
    public static void setUsername(String username) {
        loggedInUsername = username;
    }
    
    public static String getUsername() {
        return loggedInUsername;
    }
}
