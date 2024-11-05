package com.apiTests.serviceSteps.serviceSteps.user_controller;

import com.apiTests.requests.serviceRequests.user_controller.LogoutTests;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.OK;

public class LogoutSteps{

    private LogoutTests logoutTests;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(LogoutSteps.class);

    // API logout with saved access token
    @When("the user logs out from the system")
    public void logout() {

        // Send a logout request to API
        logoutTests = new LogoutTests();
        logoutTests.Logout(OK, ACCESS_TOKEN);

        logger.info("The system has been logged out with a valid access token");
    }
}