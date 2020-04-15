package com.example.softwareengineerlab2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserInfo {

    public static String USER_PASS, USER_NAME, USER_Address;


    public UserInfo(String uPass, String uName, String uAddress) {
        this.USER_NAME = uName;
        this.USER_Address = uAddress;
        this.USER_PASS = uPass;

    }

    public static boolean checkUserExistance(String userName, String userPass) {
        boolean isUserFound = false;
        Connection con = DBConnect.getConnection();
        if (con == null) {
            isUserFound = false;
        } else {
            String checkQuery = "SELECT * FROM bank.customer WHERE Id='" + userPass + "' and name ='" + userName + "'";
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(checkQuery);
                while (rs.next()) {
                    isUserFound = true;
                    break;
                }
                con.close();
            } catch (Exception ex) {
                isUserFound = false;
            }
        }
        return isUserFound;
    }
}
