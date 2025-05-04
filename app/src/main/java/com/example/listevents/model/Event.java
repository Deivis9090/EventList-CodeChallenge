package com.example.listevents.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Event", indices = {@Index(value = {"name"})})
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String date;
    private String place;
    private String image;
    private String desc;
    private int favorite;

    public Event(String name, String date, String place, String image, String desc, int favorite) {
        this.name = name;
        this.date = date;
        this.place = place;
        this.image = image;
        this.desc = desc;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
