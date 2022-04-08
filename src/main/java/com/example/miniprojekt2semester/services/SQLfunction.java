package com.example.miniprojekt2semester.services;

import java.sql.*;
import com.example.miniprojekt2semester.model.user;
import com.example.miniprojekt2semester.model.wish;
import com.example.miniprojekt2semester.model.wishList;
import java.util.ArrayList;

public class SQLfunction {
    static Connection con;
    static Statement stmt;
    static String sqlString;
    static ResultSet rs;


    //Timmie's Kode
    public Connection connectDB() {
        System.out.println("Connecting to the database");
        try {

            String url = "jdbc:mysql://localhost:3306/gavelisten";
            String user = "root";
            String password = "";

            con = DriverManager.getConnection(url, user, password);


            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);

        } catch (Exception e) {
            System.out.println("ERROR");
        }

        return con;
    }

    //Jimmy's kode
    public void closeConnection(){
        try{
            con.close();
            System.out.println("Connection terminated");
        } catch (Exception e){
            System.out.println("ERROR - Connection might already be closed");
        }
    }

    //Timmie's Kode
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
    //Jimmy's kode
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
    //Jimmy's kode
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
    //Jimmy's kode
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

    //Timmie's Kode
    public int returnWishlistID(int sessionID){
        int wishlistID = 0;
        connectDB();

        try {

            String sqlString = "SELECT wishlist_id FROM gavelisten.wishlist "+
                    "INNER JOIN gavelisten.user "+
                    "ON user.user_id = "+sessionID+" and wishlist.user_id = "+sessionID+
                    " ORDER BY wishlist.wishlist_id DESC Limit 0,1;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            rs.next();
            wishlistID = rs.getInt(1);
        }
        catch(Exception e){
            System.out.println("ERROR CONNECTING TO WISHLIST");
        }
        return wishlistID;



    }

    //Jimmy's kode
    public ArrayList<wishList> arrayOfWishlist(int sessionID){
        connectDB();
        ArrayList<wishList> listToReturn = new ArrayList<wishList>();
        int wishlistID;
        String wishlistName;
        int userID;

        try {
            sqlString = "SELECT  wishlist_id, name, wishlist.user_id FROM gavelisten.wishlist " +
            "INNER JOIN gavelisten.user " +
                    "ON user.user_id = " + sessionID + " AND wishlist.user_id = "+sessionID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            while (rs.next()){
                wishlistID = rs.getInt(1);
                wishlistName = rs.getString(2);
                userID = rs.getInt(3);
                listToReturn.add(new wishList(wishlistID, wishlistName, userID));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        closeConnection();
        return listToReturn;
    }
    //Jimmy's kode
    public ArrayList<wish> wishFromWishlistAndUserID(int wishlistID, int userID){
        connectDB();
        ArrayList<wish> listToReturn = new ArrayList<wish>();
        String wishName;
        int wishprice;
        String wishLink;

        sqlString = "SELECT wish_name, wish_price, wish_link FROM gavelisten.wishlist " +
                "INNER JOIN gavelisten.user " +
                "ON user.user_id = " + userID + " AND wishlist.user_id = " + userID + " " +
                "INNER JOIN gavelisten.wish " +
                "ON wish.wishlist_id = " + wishlistID + " and wishlist.wishlist_id = " + wishlistID;

        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            while (rs.next()){
             wishName = rs.getString(1);
             wishprice = rs.getInt(2);
             wishLink = rs.getString(3);
             listToReturn.add(new wish(wishName, wishLink, wishprice));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        closeConnection();
        return listToReturn;
    }

    //Timmie's Kode
    public void addWishToList(String productName, String priceName, String link, int wishlistID){
        connectDB();
        String insertSQL = "INSERT INTO wish " +
                "(`wish_name`,`wish_price`,`wish_link`,`wishlist_id`) " +
                "VALUES('" + productName + "','" + priceName + "','" + link + "','" + wishlistID + "');";

        try{
            stmt = con.createStatement();
            stmt.executeUpdate(insertSQL);

        } catch (Exception e){
            System.out.println("ERROR DURING UPLOADING WISH");
        }
        closeConnection();
    }
}
