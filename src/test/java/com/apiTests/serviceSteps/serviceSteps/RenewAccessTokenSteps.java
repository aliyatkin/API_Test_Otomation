package com.apiTests.serviceSteps.serviceSteps;

import com.apiTests.requests.serviceRequests.user_controller.RenewAccessTokenTests;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Data_Path.*;
import static com.apiTests.constants.StatusCode.NOT_OK;
import static com.apiTests.constants.StatusCode.OK;

public class RenewAccessTokenSteps {

    private RenewAccessTokenTests renewAccessTokenTests;
    private static final Logger logger = LogManager.getLogger(RenewAccessTokenSteps.class);


    @And("User refresh the access token with the saved access token")
    public void renewAccessToken(){

        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenTests.RenewAccessToken(OK,accessTokenPath);
        logger.info("The access token refreshed");
    }

    @And("User can not refresh the access token with empty parameter")
    public void renewAccessToken_emptyParameter(){

        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenTests.RenewAccessToken(NOT_OK,emptyJSON);
        logger.info("The access token can not refreshed");
    }

    @And("User can not refresh the access token with wrong parameter")
    public void renewAccessToken_wrongParameter(){

        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenTests.RenewAccessToken(NOT_OK,wrongAccessToken);
        logger.info("The access token can not refreshed");
    }
}
