package com.apiTests.serviceSteps.serviceSteps;

import com.apiTests.requests.serviceRequests.user_controller.LogoutTests;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.Data_Path.*;
import static com.apiTests.constants.StatusCode.UNAUTHORIZED;

public class LogoutSteps{

    private LogoutTests logoutTests;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(LogoutSteps.class);

    // API logout with saved access token
    @And("the user logs out from the system with the saved access token")
    public void logout() {

        // Send a logout request to API
        logoutTests = new LogoutTests();
        logoutTests.Logout(UNAUTHORIZED, ACCESS_TOKEN);

        logger.info("The system has been logged out with a valid access token");
    }
}