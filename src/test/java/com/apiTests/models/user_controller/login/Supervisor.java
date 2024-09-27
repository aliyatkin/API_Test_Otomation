package com.apiTests.models.user_controller.login;

public class Supervisor {
    private String name;
    private String surname;

    public Supervisor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public Supervisor(){

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
}
