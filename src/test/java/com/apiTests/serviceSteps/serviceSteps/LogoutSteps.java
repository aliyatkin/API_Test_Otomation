package com.apiTests.serviceSteps.serviceSteps;

import com.apiTests.requests.serviceRequests.user_controller.LogoutTests;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.apiTests.constants.StatusCode.NOT_OK;

public class LogoutSteps{

    private String accessToken;
    private LogoutTests logoutTests;
    private static final Logger logger = LogManager.getLogger(LogoutSteps.class);


    @And("User logs out from the system with the saved access token")
    public void logout() {
        logoutTests = new LogoutTests();
        logoutTests.Logout(accessToken,NOT_OK);
        logger.info("The system has been logged out with a valid access token");
    }
}
