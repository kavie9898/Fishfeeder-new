package com.fishfeeder_new.m_DataObject;

/**
 * Created by Jasmeet on 1/24/2018.
 */

public class Androidcamera {

    int id;
    String date;
    String time;
    String image;

    public int getId() {
        return id;
    }

    public String getTime() {return time;}

    public void setTime(String time) {this.time = time;}

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


