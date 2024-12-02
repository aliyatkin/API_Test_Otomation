package com.apiTests.serviceSteps.serviceTestSteps.userController;

import com.apiTests.models.user_controller.renewAccessToken.RenewAccessTokenResponse;
import com.apiTests.requests.serviceRequests.user_controller.RenewAccessTokenTests;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.*;
import static com.apiTests.requests.HelperMethod.writeStringToFile;

public class RenewAccessTokenSteps {

    public static String accessToken;
    private RenewAccessTokenTests renewAccessTokenTests;
    public static RenewAccessTokenResponse renewAccessTokenResponse;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(RenewAccessTokenSteps.class);

    // API tries to renew access token with saved access token
    @When("the user refreshes the access token using the saved access token")
    public void renewAccessToken() {
        // Send a "renew access token" request to API
        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenResponse = renewAccessTokenTests.renewAccessToken(OK, ACCESS_TOKEN);
        accessToken = renewAccessTokenResponse.getAccessToken();
    }

    @Then("the access token is refreshed and saved")
    public void renewAccessTokenCheck() {
        if (accessToken != null) {
            writeStringToFile(accessToken, ACCESS_TOKEN);

            logger.info("The access token is refreshed and saved: {}", accessToken);
            Allure.addAttachment("The access token is refreshed and saved", accessToken);
        } else {
            logger.error("The access token is not refreshed and a error message is displayed");
        }
    }

    // API tries to renew access token with saved access token
    @When("the user tries to refresh the access token with an empty parameter")
    public void renewAccessToken_emptyParameter() {
        // Send a "renew access token" request to API
        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenResponse = renewAccessTokenTests.renewAccessToken(UNAUTHORIZED, EMPTY_JSON);
    }

    @Then("the access token is not refreshed and an error message is displayed")
    public void renewAccessTokenCheckEmptyParameter() {
        if (renewAccessTokenResponse == null) {
            logger.info("The access token is not refreshed and an error message is displayed");
        }
    }

    // API tries to renew access token with saved access token
    @When("the user tries to refresh the access token with an incorrect parameter")
    public void renewAccessToken_wrongParameter() {
        // Send a "renew access token" request to API
        renewAccessTokenTests = new RenewAccessTokenTests();
        renewAccessTokenResponse = renewAccessTokenTests.renewAccessToken(SERVER_ERROR, WRONG_ACCESS_TOKEN);
    }

    @Then("the access token is not refreshed, and an error message is displayed")
    public void renewAccessTokenCheckWrongParameter() {
        if (renewAccessTokenResponse == null) {
            logger.info("The access token is not refreshed and an error message is displayed");
        }
    }
}