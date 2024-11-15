package com.apiTests.models.zone_controller;

public class ZonesResponse {

    private int id;
    private String name;

    public ZonesResponse(){

    }
    public ZonesResponse(int id, String name) {
        this.id = id;
        this.name = name;
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
}
