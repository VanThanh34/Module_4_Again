package com.example.demo.entity;

import java.time.LocalDate;

public class Player {
    private int id;
    private String name;
    private LocalDate dob;
    private String experience;
    private String position;
    private String avatar;

    public Player() {
    }

    public Player(int id, String name, LocalDate dob, String experience, String position, String avatar) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.experience = experience;
        this.position = position;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
