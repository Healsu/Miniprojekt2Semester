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
            connectDB();
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
            return new user(id,username, userEmail);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void createWishList(int id, String wishlistName ){
        connectDB();
        sqlString = "INSERT INTO wishlist (`user_id`, `name`) VALUES ('" + id + "', '" + wishlistName + "');";
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sqlString);
            closeConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
        closeConnection();
    }

    public int returnWishlistID(int seassionID){
        int wishlistID = 0;
        connectDB();

        try {

            String sqlString = "SELECT wishlist_id FROM gavelisten.wishlist "+
                    "INNER JOIN gavelisten.user "+
                    "ON user.user_id = "+seassionID+" and wishlist.user_id = "+seassionID+
                    " ORDER BY wishlist.wishlist_id DESC Limit 0,1;";
            stmt = con.createStatement();
            wishlistID = stmt.executeUpdate(sqlString);
        }
        catch(Exception e){
            System.out.println("ERROR CONNECTING TO WISHLIST");
        }
        return wishlistID;



    }
    public void addWishToList(String productName, String priceName, String link, int wishlistID){

        String insertSQL = "INSERT INTO wish " +
                "(`wish_name`,`wish_price`,`wish_link`,`wishlist_id`) " +
                "VALUES('" + productName + "','" + priceName + "','" + link + "'"+wishlistID+"','"+
                ");";

        try{
            stmt = con.createStatement();
            stmt.executeUpdate(insertSQL);

        } catch (Exception e){
            System.out.println("ERROR DURING UPLOADING WISH");
        }
    }
}
