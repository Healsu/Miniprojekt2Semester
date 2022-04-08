// Samuel's kode
package com.example.miniprojekt2semester.model;

public class wishList {
    private int wishListID;
    private String wishListName;
    private int userID;

    public wishList(int wishListID, String wishListName, int userID) {
        this.wishListID = wishListID;
        this.wishListName = wishListName;
        this.userID = userID;
    }

    public int getWishListID() {
        return wishListID;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }

    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
