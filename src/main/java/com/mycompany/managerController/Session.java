package com.mycompany.managerController;

public class Session {

    private static int managerId;
    private static String managerName;
    private static String managerUsername;

    public static void setManager(int id, String name, String username) {
        managerId = id;
        managerName = name;
        managerUsername = username;
    }

    public static int getManagerId() {
        return managerId;
    }

    public static String getManagerName() {
        return managerName;
    }

    public static String getManagerUsername() {
        return managerUsername;
    }
}
