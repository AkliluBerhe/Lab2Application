package com.example.softwareengineerlab2;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
  public static Connection getConnection(){
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);

    Connection con = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/bank","abdul","abdul");
    } catch (ClassNotFoundException | SQLException ex) {
    }


    return con;
  }
}