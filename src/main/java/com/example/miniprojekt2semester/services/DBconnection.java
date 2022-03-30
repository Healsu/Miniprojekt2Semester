package com.example.miniprojekt2semester.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBconnection {
    static Connection con;
    static Statement stmt;
    static String sqlString;
    static ResultSet rs;


    public Connection connectDB(){
        System.out.println("Connecting to the database");

        try{

        String url = "test";

        con = DriverManager.getConnection(url,"root","password");

        System.out.println("URL: " + url);
        System.out.println("Connection: " + con);

    } catch (Exception e) {
        System.out.println("ERROR");
    }

        return con;
    }
}
