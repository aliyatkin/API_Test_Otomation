package com.apiTests.models.detection_controller.DetectionsClassificationTypes;

public class DetectionsClassificationTypesResponse {

    private int id;
    private String name;

    public DetectionsClassificationTypesResponse(){

    }

    public DetectionsClassificationTypesResponse(int id, String name) {
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
