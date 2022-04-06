package com.example.miniprojekt2semester.services;

import java.sql.*;
import com.example.miniprojekt2semester.model.user;
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
            String user = "root";
            String password = "Tim10ses";

            con = DriverManager.getConnection(url, user, password);


            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);

        } catch (Exception e) {
            System.out.println("ERROR");
        }

        return con;
    }
    public boolean addUserToDB(String userName, String userMail, String userPassword) {
        String insertSQL = "INSERT INTO user " +
                "(`user_name`,`user_mail`,`user_password`) " +
                "VALUES('" + userName + "','" + userMail + "','" + userPassword + "');";

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

    public boolean checkIfUserExists(String email, String password){
        connectDB();
        try {
            sqlString = "SELECT * FROM user WHERE user_mail = '" + email + "' AND user_password = '" + password + "';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            if (rs.next()){
                closeConnection();
                return true;
            } else {
                closeConnection();
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        closeConnection();
        return false;
    }
    public user userForSession(String email, String password){
        connectDB();
        try {
            sqlString = "SELECT * FROM user WHERE user_mail = '" + email + "' AND user_password = '" + password + "';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            rs.next();
            int id = rs.getInt(1);
            String username = rs.getString(2);
            String userEmail = rs.getString(3);
            String userPassword = rs.getString(4);
            return new user(id,username, userEmail, userPassword);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String createWishList( ){

        return null;
    }
    public String addWishToList(String productName, String priceName, String link){
        String insertSQL = "INSERT INTO wish " +
                "() " +
                "VALUES('" + productName + "','" + priceName + "','" + link + "');";
        return null;
    }
}
