package com.example.miniprojekt2semester.services;

import java.sql.*;
import java.util.ArrayList;

public class SQLfunction {
    static Connection con;
    static Statement stmt;
    static String sqlString;
    static ResultSet rs;


    public Connection connectDB() {
        System.out.println("Connecting to the database");
        try {

            String url = "jdbc:mysql://localhost:3306/gavelisten";


            con = DriverManager.getConnection(url, "root", "Tim10ses");


            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);

        } catch (Exception e) {
            System.out.println("ERROR");
        }

        return con;
    }

    public boolean addUserToDB(String brugerNavn, String brugerMail, String brugerKodeord) {
        String insertSQL = "INSERT INTO user " +
                "(`user_name`,`user_mail`,`user_password`) " +
                "VALUES('" + brugerNavn + "','" + brugerMail + "','" + brugerKodeord + "');";

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(insertSQL);
            closeConnection();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void closeConnection(){
        try{
            con.close();
            System.out.println("Connection terminated");
        } catch (Exception e){
            System.out.println("ERROR - Connection might already be closed");
        }
    }
}
