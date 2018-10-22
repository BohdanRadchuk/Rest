package com.lab.restapp.entity;

import java.util.List;


public class User {
    private int id;
    private String name;
    private List<Device> devices;

    public User(int id, String name, List<Device> devices) {
        this.id = id;
        this.name = name;
        this.devices = devices;
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

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", devices=" + devices +
                '}';
    }
}
