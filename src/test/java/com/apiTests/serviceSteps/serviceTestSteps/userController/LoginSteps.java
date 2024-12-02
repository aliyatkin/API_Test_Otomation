package com.apiTests.serviceSteps.serviceTestSteps.userController;

import com.apiTests.models.user_controller.login.LoginResponse;
import com.apiTests.requests.serviceRequests.user_controller.LoginTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.*;
import static com.apiTests.requests.HelperMethod.*;

public class LoginSteps {

    // Used for JSON processing and comparison
    private ObjectMapper objectMapper = new ObjectMapper();
    public static String accessToken;
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private LoginTests loginTests;
    private LoginResponse loginResponse;

    // Helper method to perform login and check response
    private void performLogin(String username, String password, int expectedStatusCode, boolean isHashedPassword) {
        loginTests = new LoginTests();
        loginResponse = loginTests.login(expectedStatusCode, username, password, isHashedPassword);
    }

    // Helper method for response validation
    private void validateResponseUsername(String requestBodyPath) throws JsonProcessingException {
        String username = loginResponse.getUser().getUsername();
        String requestBody = requestBodyLoader(requestBodyPath);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        if (jsonNode.get("username").asText().equals(username)) {
            logger.info("The username in the response matches the request");
            Allure.addAttachment("username check", "The username in the response matches the request");
        } else {
            logger.error("The username in the response does not match the request");
            Allure.addAttachment("username check", "The username in the response does not match the request");
            Assertions.fail("The username in the response does not match the request");
        }
    }

    @When("the user tries to log in with a valid {string} and an invalid {string}")
    public void invalidPassword(String username, String password) {
        performLogin(username, password, UNAUTHORIZED, true);
    }

    @Then("the user cannot log in to the system with a valid username and an invalid password")
    public void invalidPasswordCheck() {
        if (loginResponse == null) {
            logger.info("The system has not logged in with a valid username and invalid password");
        }
    }

    @When("the user tries to log in with a valid {string} and an empty {string}")
    public void emptyPassword(String username, String password) {
        performLogin(username, password, UNAUTHORIZED, true);
    }

    @Then("the user cannot log in to the system with a valid username and an empty password")
    public void emptyPasswordCheck() {
        if (loginResponse == null) {
            logger.info("The system has not logged in with a valid username and empty password");
        }
    }

    @When("the user tries to log in with a valid {string} and {string}")
    public void validLogin(String username, String password) {
        performLogin(username, password, OK, true);
        logger.info("The system has been logged in with a valid username and password");
    }

    @Then("the system verifies the required response parameters")
    public void checkParameter() throws JsonProcessingException {
        validateResponseUsername(V_USERNAME_PASSWORD);
    }

    @And("the access token is saved")
    public void saveAccessToken() {
        accessToken = loginResponse.getTokenDetails().getAccessToken();
        writeStringToFile(accessToken, ACCESS_TOKEN);
        logger.info("The Access Token has been saved: " + accessToken);
        Allure.addAttachment("Saved access token", accessToken);
    }

    @When("the user tries to log in with a valid {string} and a hashed password with a {string} false query")
    public void hashedPasswordAPI(String username, String trueOrFalse) {
        boolean torf = Boolean.parseBoolean(trueOrFalse);
        String password = requestBodyLoader(HASHED_PASSWORD);
        performLogin(username, password, OK, torf);
        logger.info("The system has been logged in with a valid username and hashed password when query parameter is false");
    }

    @Then("the system verifies the required response parameters using the hashed password")
    public void checkParameterHashedPassword() throws JsonProcessingException {
        validateResponseUsername(V_USERNAME_H_PASSWORD);
    }

    @And("the access token is saved using the hashed password")
    public void saveAccessTokenHashedPassword() {
        accessToken = loginResponse.getTokenDetails().getAccessToken();
        logger.info("The Access Token has been saved: " + accessToken);
        Allure.addAttachment("Saved access token", accessToken);
    }

    @When("the user tries to log in with a valid {string} and a hashed password with a {string} true query")
    public void hashedPasswordTrueQuery(String username, String trueOrFalse) {
        boolean torf = Boolean.parseBoolean(trueOrFalse);
        String password = requestBodyLoader(HASHED_PASSWORD);
        performLogin(username, password, UNAUTHORIZED, torf);
    }

    @Then("the user cannot log in to the system with a valid username, hashed password, and a true query")
    public void hashedPasswordCheck() {
        if (loginResponse == null) {
            logger.info("The system has not logged in with a valid username, hashed password, and a true query");
        }
    }
}