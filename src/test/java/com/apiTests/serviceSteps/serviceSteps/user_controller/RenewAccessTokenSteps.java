package com.apiTests.serviceSteps.serviceSteps.user_controller;

import com.apiTests.requests.serviceRequests.user_controller.RenewAccessTokenTests;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.*;

public class RenewAccessTokenSteps {

    private RenewAccessTokenTests renewAccessTokenTests;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(RenewAccessTokenSteps.class);

    // API renew access token with saved access token
    @And("the user refresh the access token with the saved access token")
    public void renewAccessToken(){

        // Send a renew access token request to API
        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenTests.RenewAccessToken(OK,ACCESS_TOKEN);

        logger.info("The access token refreshed");
    }

    // API renew access token with saved access token
    @And("the user can not refresh the access token with empty parameter")
    public void renewAccessToken_emptyParameter(){

        // Send a renew access token request to API
        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenTests.RenewAccessToken(UNAUTHORIZED,EMPTY_JSON);

        logger.info("The access token can not refreshed");
    }

    // API renew access token with saved access token
    @And("the user can not refresh the access token with wrong parameter")
    public void renewAccessToken_wrongParameter(){

        // Send a renew access token request to API
        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenTests.RenewAccessToken(UNAUTHORIZED,WRONG_ACCESS_TOKEN);

        logger.info("The access token can not refreshed");
    }
}