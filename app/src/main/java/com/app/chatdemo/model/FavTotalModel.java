package com.app.chatdemo.model;

/**
 * Created by swapna on 6/3/17.
 */
public class FavTotalModel {
    int favTotal;
    String userName;

    @Override
    public String toString() {
        return "FavTotalModel{" +
                "favTotal=" + favTotal +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getFavTotal() {
        return favTotal;
    }

    public void setFavTotal(int favTotal) {
        this.favTotal = favTotal;
    }
}
