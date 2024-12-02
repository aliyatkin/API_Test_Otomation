package com.apiTests.serviceSteps.serviceTestSteps.userController;

import com.apiTests.requests.serviceRequests.user_controller.LogoutTests;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.*;
import static org.junit.Assert.assertEquals;

public class LogoutSteps {

    private LogoutTests logoutTests;
    public static Response response;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(LogoutSteps.class);

    // API logout with saved access token
    @When("the user logs out of the system")
    public void logout() {
        // Send a logout request to the API
        logoutTests = new LogoutTests();
        response = logoutTests.logout(OK, ACCESS_TOKEN);
    }

    @Then("the system successfully logs the user out")
    public void logoutSuccessCheck() {
        assertEquals(OK, response.getStatusCode());
        logger.info("The system has successfully logged the user out.");
    }

    @When("the user tries to log out of the system with an invalid token")
    public void failLogout() {
        // Send a logout request to the API with an invalid token
        logoutTests = new LogoutTests();
        response = logoutTests.logout(SERVER_ERROR, WRONG_ACCESS_TOKEN);
    }

    @Then("the system fails to log the user out")
    public void logoutFailCheck() {
        assertEquals(SERVER_ERROR, response.getStatusCode());
        logger.info("The system failed to log the user out.");
    }
}