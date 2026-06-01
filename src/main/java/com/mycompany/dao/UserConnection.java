package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserConnection {
    public String loginUser(String uname, String pass)
    {
        String role = null;
        
        try(Connection con = DatabaseConnection.getConnection())
        {
            String login = "SELECT role FROM User WHERE user_name = ? AND password = ?";
            PreparedStatement P = con.prepareStatement(login);
            P.setString(1, uname);
            P.setString(2, pass);
            
            ResultSet r = P.executeQuery();
            if(r.next())
            {
                role = r.getString("role");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return role;
    }
}
