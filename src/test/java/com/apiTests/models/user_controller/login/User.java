package com.apiTests.models.user_controller.login;

public class User {
    private String name;
    private String surname;
    private String username;
    private Detail detail;
    private String pushToken;
    private int userLevelId;
    private String userLevelName;
    private int id;

    public User(String name, String surname, String username, Detail detail, String pushToken, int userLevelId, String userLevelName, int id) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.detail = detail;
        this.pushToken = pushToken;
        this.userLevelId = userLevelId;
        this.userLevelName = userLevelName;
        this.id = id;
    }
    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public int getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(int userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getUserLevelName() {
        return userLevelName;
    }

    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
