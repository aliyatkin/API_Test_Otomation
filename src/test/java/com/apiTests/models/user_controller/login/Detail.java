package com.apiTests.models.user_controller.login;

public class Detail {
    private String identityNumber;
    private String email;
    private String phoneNumber;
    private String rank;
    private String profilePicture;
    private Supervisor supervisor;

    public Detail(String identityNumber, String email, String phoneNumber, String rank, String profilePicture, Supervisor supervisor) {
        this.identityNumber = identityNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.profilePicture = profilePicture;
        this.supervisor = supervisor;
    }

    public Detail() {

    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
}
