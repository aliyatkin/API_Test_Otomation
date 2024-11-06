package com.apiTests.serviceSteps.serviceTestSteps.user_controller;

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
    ObjectMapper objectMapper = new ObjectMapper();
    public String accessToken;
    private LoginTests loginTests;
    private LoginResponse loginResponse;

    // Logger for tracking actions and output
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    //API login with valid username and invalid password
    @When("the user logs in with a valid {string} and an invalid {string}")
    public void invalidPassword(String username, String password){

        // Send a login request to API
        loginTests = new LoginTests();

        loginResponse = loginTests.Login(UNAUTHORIZED,username, password,true);

        logger.info("The system has not been logged in with a valid username and invalid password");
    }

    @When("the user logs in with a valid {string} and an empty {string}")
    public void emptyPassword(String username, String password){

        // Send a login request to API
        loginTests = new LoginTests();
        loginResponse = loginTests.Login(UNAUTHORIZED, username, password, true);  // Save the response in loginResponse

        logger.info("The system has not been logged in with a valid username and empty password");
    }


    @When("the user logs in with a valid {string} and {string}")
    public void valid(String username, String password) {

        // Send a login request to API
        loginTests = new LoginTests();
        loginResponse = loginTests.Login(OK, username, password,true);

        logger.info("The system has been logged in with a valid username and password");
    }

    // Verifies the response parameters after mock login
    @Then("the system verifies the required response parameters")
    public void checkParameter() throws JsonProcessingException {

        String username = loginResponse.getUser().getUsername();
        String requestBody = requestBodyLoader(V_USERNAME_PASSWORD);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        // Check if the username in the response matches the request
        if (jsonNode.get("username").asText().equals(username)) {
            logger.info("The username in the response matches the request");
            Allure.addAttachment("username check","The username in the response matches the request");
        } else {
            logger.error("The username in the response does not match the request");
            Allure.addAttachment("username check","The username in the response does not matches the request");
            Assertions.fail("The username in the response does not matches the request");
        }
    }

    // Saves the access token returned from the API
    @And("the access token is saved")
    public void saveAccessToken() {

        // Save the access token and write it to a file given to the method
        accessToken = loginResponse.getTokenDetails().getAccessToken();
        writeStringToFile(accessToken,ACCESS_TOKEN);

        logger.info("The Access Token has been saved: " + accessToken);
        Allure.addAttachment("Access Token", accessToken);
    }

    @When("the user logs in with a valid {string} and a hashed password with a {string} false query")
    public void hashedPasswordAPI(String username, String trueOrFalse){

        // Convert from string to boolean
        boolean torf = Boolean.parseBoolean(trueOrFalse);

        // Load the request body from the specified file path
        String password = requestBodyLoader(HASHED_PASSWORD);

        // Send a login request to API
        loginTests = new LoginTests();
        loginResponse = loginTests.Login(OK, username, password, torf);  // Save the response in loginResponse

        logger.info("The system has been logged in with a valid username and hashed password when query parameter is false");
    }

    @Then("the system verifies the required response parameters using the hashed password")
    public void checkParameterHashedPassword() throws JsonProcessingException{

        String username = loginResponse.getUser().getUsername();
        String requestBody = requestBodyLoader(V_USERNAME_H_PASSWORD);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        // Check if the username in the response matches the request
        if (jsonNode.get("username").asText().equals(username)) {
            logger.info("The username in the response matches the one in the request");
            Allure.addAttachment("username check","The username in the response matches the one in the request");
        } else {
            logger.error("The username in the response does not match the one in the request");
            Allure.addAttachment("username check","The username in the response does not matches the one in the request");
            Assertions.fail("The username in the response does not match the one in the request");
        }
    }

    @And("the access token is saved using the hashed password")
    public void saveAccessTokenHashedPassword(){

        // Save access token
        accessToken = loginResponse.getTokenDetails().getAccessToken();

        logger.info("The Access Token has been saved: " + accessToken);
        Allure.addAttachment("Access Token", accessToken);
    }

    @When("the user logs in with a valid {string} and a hashed password with a {string} true query")
    public void hashedPasswordTrueQuery(String username, String trueOrFalse){

        // Convert from string to boolean
        boolean torf = Boolean.parseBoolean(trueOrFalse);

        // Load the request body from the specified file path
        String password = requestBodyLoader(HASHED_PASSWORD);

        // Send a login request to API
        loginTests = new LoginTests();
        loginResponse = loginTests.Login(UNAUTHORIZED, username, password, torf);

        logger.info("The system has not been logged in with a valid username and hashed password when query parameter is true");
    }
}