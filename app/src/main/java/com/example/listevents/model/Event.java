package com.example.listevents.model;

public class Event {

    private String name;
    private String gender;
    private String age;
    private int image;
    private int desc;

    public Event(String name, String gender, int desc, String age, int image) {
        this.name = name;
        this.gender = gender;
        this.desc = desc;
        this.age = age;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }
}
