// Samuel's kode
package com.example.miniprojekt2semester.model;

public class wish {
    private String wishLink;
    private String wishName;
    private int wishPrice;
    private int wishListID;
    private boolean isWishTaken;

    public wish(String wishLink, String wishName, int wishPrice, int wishListID, boolean isWishTaken) {
        this.wishLink = wishLink;
        this.wishName = wishName;
        this.wishPrice = wishPrice;
        this.wishListID = wishListID;
        this.isWishTaken = isWishTaken;
    }

    public wish(String wishName, String wishLink, int wishPrice){
        this.wishLink = wishLink;
        this.wishName = wishName;
        this.wishPrice = wishPrice;
    }

    public String getWishLink() {
        return wishLink;
    }

    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public int getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(int wishPrice) {
        this.wishPrice = wishPrice;
    }

    public int getWishListID() {
        return wishListID;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }

    public boolean isWishTaken() {
        return isWishTaken;
    }

    public void setWishTaken(boolean wishTaken) {
        isWishTaken = wishTaken;
    }
}
