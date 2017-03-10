package com.app.chatdemo.model;

/**
 * Created by swapna on 5/3/17.
 */
public class MessagesDisplayModel {

    String ID;
    String userName;
    String messages;
    String image;
    String name;
    String timeStamp;
    int me;
    int myFav;

    public int getMyFav() {
        return myFav;
    }

    public void setMyFav(int myFav) {
        this.myFav = myFav;
    }

    public int getMe() {
        return me;
    }

    public void setMe(int me) {
        this.me = me;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


    public MessagesDisplayModel() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }


    @Override
    public String toString() {
        return "MessagesDisplayModel{" +
                "ID='" + ID + '\'' +
                ", userName='" + userName + '\'' +
                ", messages='" + messages + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", me=" + me +
                ", myFav=" + myFav +
                '}';
    }

    public MessagesDisplayModel(String userName, String messages, String image, String name, String timeStamp, int me, int numfav) {
        this.userName=userName;
        this.messages=messages;
        this.image=image;
        this.name=name;
        this.timeStamp=timeStamp;
        this.me=me;
this.myFav=numfav;
    }




}
