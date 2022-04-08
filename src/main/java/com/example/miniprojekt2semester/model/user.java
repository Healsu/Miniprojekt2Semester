// Samuel's kode
package com.example.miniprojekt2semester.model;

public class user {
    private int userID;
    private String userName;
    private String userMail;
    private String userPassword;

    public user(int userID, String userName, String userMail, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
    }

    public user(int userID, String userName, String userMail) {
        this.userID = userID;
        this.userName = userName;
        this.userMail = userMail;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "user{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
