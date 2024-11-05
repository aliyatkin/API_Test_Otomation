package com.apiTests.serviceSteps.mockTestSteps;

import com.apiTests.mockService.LoginMockService;
import com.apiTests.models.user_controller.login.LoginResponse;
import com.apiTests.requests.mockRequests.user_controller.LoginMockTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.apiTests.constants.DataPath.*;
import static com.apiTests.constants.StatusCode.*;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;

public class MockLoginSteps {

    // Used for JSON processing and comparison
    ObjectMapper objectMapper = new ObjectMapper();

    // Variables to store access token and login response
    private String accessToken;
    private LoginResponse loginResponse;
    private LoginMockTest loginMockTests;
    private static LoginMockService loginMockService;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(MockLoginSteps.class);

    // Mock login with a valid username and invalid password
    @Given("the user logs in with a valid {string} and an invalid {string} using the mock service")
    public void invalidPassword_mock(String username, String password) {

        // Initialize and start the mock service for login
        loginMockService = new LoginMockService();
        loginMockService.startMockServer();

        // Set up the mock request and response for the login with invalid password
        loginMockService.setupLoginMock(UNAUTHORIZED, V_USERNAME_I_PASSWORD, NULL_JSON, "true");

        // Send a login request using mock data
        loginMockTests = new LoginMockTest();
        loginResponse = loginMockTests.LoginForMock(UNAUTHORIZED, username, password, true);

        logger.info("The system has not been logged in with a valid username and invalid password");

        // Stop the mock server after test
        loginMockService.stopMockServer();
    }

    // Mock login with a valid username and empty password
    @Given("the user logs in with a valid {string} and an empty {string} using the mock service")
    public void emptyPassword_mock(String username, String password) {

        // Initialize and start the mock service for login
        loginMockService = new LoginMockService();
        loginMockService.startMockServer();

        // Set up the mock request and response for login with empty password
        loginMockService.setupLoginMock(UNAUTHORIZED, V_USERNAME_E_PASSWORD, NULL_JSON, "true");

        // Execute login request with mock data
        loginMockTests = new LoginMockTest();
        loginResponse = loginMockTests.LoginForMock(UNAUTHORIZED, username, password, true);

        logger.info("The system has not been logged in with a valid username and empty password");

        // Stop the mock server after the test
        loginMockService.stopMockServer();
    }

    // Mock login with valid username and password
    @Given("the user logs in with a valid {string} and {string} using the mock service")
    public void valid_mock(String username, String password) {

        // Initialize and start the mock service for login
        loginMockService = new LoginMockService();
        loginMockService.startMockServer();

        // Set up the mock request and response for the login with valid credentials
        loginMockService.setupLoginMock(OK, V_USERNAME_PASSWORD, MOCK_LOGIN_RESPONSE_BODY, "true");

        // Execute login request
        loginMockTests = new LoginMockTest();
        loginResponse = loginMockTests.LoginForMock(OK, username, password, true);

        logger.info("The system has been logged in with a valid username and password");
    }

    // Verifies the response parameters after mock login
    @When("the system verifies the required response parameters for the mock service")
    public void checkParameter_mock() throws JsonProcessingException {

        String username = loginResponse.getUser().getUsername();
        String requestBody = requestBodyLoader(V_USERNAME_PASSWORD);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        // Check if the username in the response matches the request
        if (jsonNode.get("username").asText().equals(username)) {
            logger.info("The username in the response matches the request");
            Allure.addAttachment("Username Check", "The username in the response matches the request");
        } else {
            logger.error("The username in the response does not match the one in the request");
            Allure.addAttachment("Username Check", "The username in the response does not match the request");
            Assertions.fail("The username in the response does not match the request");
        }
    }

    // Saves the access token returned from the mock service
    @Then("the access token is saved for the mock service")
    public void saveAccessToken_mock() {

        accessToken = loginResponse.getTokenDetails().getAccessToken();
        logger.info("The Access Token has been saved: {}", accessToken);
        Allure.addAttachment("Access Token", accessToken);

        // Stop the mock server after test
        loginMockService.stopMockServer();
    }

    // Mock login with hashed password
    @Given("the user logs in with a valid {string} and hashed password with a {string} false query using the mock service")
    public void hashedPassword_mock(String username, String trueOrFalse) {

        // Convert from string to boolean
        boolean torf = Boolean.parseBoolean(trueOrFalse);

        // Load the request body from the specified file path
        String password = requestBodyLoader(HASHED_PASSWORD);

        // Initialize and start the mock service for login
        loginMockService = new LoginMockService();
        loginMockService.startMockServer();

        // Set up the mock request and response for the login with hashed password
        loginMockService.setupLoginMock(OK, V_USERNAME_H_PASSWORD, MOCK_LOGIN_RESPONSE_BODY, "false");

        // Execute login request with hashed password
        loginMockTests = new LoginMockTest();
        loginResponse = loginMockTests.LoginForMock(OK, username, password, torf);

        logger.info("The system has been logged in with a valid username and hashed password");
    }

    // Verifies the response when using hashed password
    @When("the system verifies the required response parameters using the hashed password for the mock service")
    public void checkParameterHashedPassword_mock() throws JsonProcessingException {

        String username = loginResponse.getUser().getUsername();
        String requestBody = requestBodyLoader(V_USERNAME_H_PASSWORD);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        // Check if the username in the response matches the request
        if (jsonNode.get("username").asText().equals(username)) {
            logger.info("The username in the response matches the one in the request");
            Allure.addAttachment("Username Check", "The username in the response matches the request");
        } else {
            logger.error("The username in the response does not match the one in the request");
            Allure.addAttachment("Username Check", "The username in the response does not match the request");
            Assertions.fail("The username in the response does not match the request");
        }
    }

    // Saves the access token when using hashed password
    @Then("the access token is saved using the hashed password for the mock service")
    public void saveAccessTokenHashedPassword_mock() {

        accessToken = loginResponse.getTokenDetails().getAccessToken();
        logger.info("The Access Token has been saved: {}", accessToken);
        Allure.addAttachment("Access Token", accessToken);

        // Stop the mock server after test
        loginMockService.stopMockServer();
    }

    // Mock login with a valid username, hashed password, and true query
    @Given("the user logs in with a valid {string} and hashed password with a {string} true query using the mock service")
    public void hashedPasswordTrueQuery_mock(String username, String trueOrFalse) {

        // Convert from string to boolean
        boolean torf = Boolean.parseBoolean(trueOrFalse);

        // Load the request body from the specified file path
        String password = requestBodyLoader(HASHED_PASSWORD);

        // Initialize and start the mock service for login
        loginMockService = new LoginMockService();
        loginMockService.startMockServer();

        // Set up the mock request and response for the login with hashed password when query is True
        loginMockService.setupLoginMock(UNAUTHORIZED, V_USERNAME_H_PASSWORD, NULL_JSON, "true");

        // Execute login request with hashed password when query is True
        loginMockTests = new LoginMockTest();
        loginResponse = loginMockTests.LoginForMock(UNAUTHORIZED, username, password, torf);

        logger.info("The system has not been logged in with a valid username and password");

        // Stop the mock server after test
        loginMockService.stopMockServer();
    }
}