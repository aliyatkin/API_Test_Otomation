package com.apiTests.models.user_controller.login;

import java.util.List;

public class LoginResponse {
    private User user;
    private TokenDetail tokenDetails;
    private List<String> authorities;

    public LoginResponse(User user, TokenDetail tokenDetails, List<String> authorities) {
        this.user = user;
        this.tokenDetails = tokenDetails;
        this.authorities = authorities;
    }
    public LoginResponse(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TokenDetail getTokenDetails() {
        return tokenDetails;
    }

    public void setTokenDetails(TokenDetail tokenDetails) {
        this.tokenDetails = tokenDetails;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
