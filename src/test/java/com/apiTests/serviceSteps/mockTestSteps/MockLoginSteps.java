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

import static com.apiTests.constants.Data_Path.*;
import static com.apiTests.constants.StatusCode.*;
import static com.apiTests.requests.HelperMethod.requestBodyLoader;

public class MockLoginSteps {

    ObjectMapper objectMapper = new ObjectMapper();

    private String accessToken;
    private LoginResponse loginResponse;
    private LoginMockTest loginMockTests;
    private static LoginMockService loginMockService;
    private static final Logger logger = LogManager.getLogger(MockLoginSteps.class);

    @Given("User logs into the system with a valid username and invalid password for Mock Service")
    public void validUsernameAndInvalidPasswordForMock(){

        loginMockService = new LoginMockService();
        loginMockService.startMockServer();
        loginMockService.setupLoginMock(NOT_OK, mockResponseBodyInvalid, validUsernameInvalidPassword, "true");

        loginMockTests = new LoginMockTest();  // Create the LoginMockTest class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameInvalidPassword,NOT_OK,true);  // Save the response in loginResponse

        logger.info("The system has been logged in with a valid username and password");

        loginMockService.stopMockServer();
    }
    @Given("User logs into the system with a valid username and empty password for Mock Service")
    public void validUsernameAndEmptyPasswordForMock(){

        loginMockService = new LoginMockService();
        loginMockService.startMockServer();
        loginMockService.setupLoginMock(NOT_OK, mockResponseBodyEmpty, validUsernameAndEmptyPassword, "true");

        loginMockTests = new LoginMockTest();  // Create the LoginMockTest class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameAndEmptyPassword,NOT_OK,true);  // Save the response in loginResponse

        logger.info("The system has not been logged in with a valid username and password");

        loginMockService.stopMockServer();
    }

    @Given("User logs into the system with a valid username and password for Mock Service")
    public void validUsernameAndPasswordForMock() {

        loginMockService = new LoginMockService();
        loginMockService.startMockServer();
        loginMockService.setupLoginMock(OK, mockResponseBody, validUsernameAndPassword, "true");

        loginMockTests = new LoginMockTest();  // Create the LoginMockTest class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameAndPassword,OK,true);  // Save the response in loginResponse

        logger.info("The system has not been logged in with a valid username and password");
    }

    @When("Verifying the required response parameters for Mock Service")
    public void checkParameterForMock() throws JsonProcessingException {

        String username = loginResponse.getUser().getUsername();
        String requestBody = requestBodyLoader(validUsernameAndPassword);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        // Username check
        if (jsonNode.get("username").asText().equals(username)) {
            logger.info("The username in the response matches the one in the request");
            Allure.addAttachment("username check","The username in the response matches the one in the request");
        } else {
            logger.error("The username in the response does not match the one in the request");
            Allure.addAttachment("username check","The username in the response does not matches the one in the request");
            Assertions.fail("The username in the response does not match the one in the request");
        }
    }

    @Then("Save the access token for Mock Service")
    public void saveAccessTokenForMock() {

        // Save access token
        accessToken = loginResponse.getTokenDetails().getAccessToken();
        logger.info("The Access Token has been saved: " + accessToken);
        Allure.addAttachment("Access Token", accessToken);

        loginMockService.stopMockServer();
    }

    @Given("User logs into the system with a valid username and hashed password for Mock Service")
    public void validUsernameAndHashedPassword(){

        loginMockService = new LoginMockService();
        loginMockService.startMockServer();
        loginMockService.setupLoginMock(OK, mockResponseBody, validUsernameAndHashedPassword,"false");

        loginMockTests = new LoginMockTest();  // Create the LoginMockTest class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameAndHashedPassword, OK,false);  // Save the response in loginResponse

        logger.info("The system has been logged in with a valid username and hashed password");
    }
    @When("Verifying the required response parameters for Mock Service by using hashed password")
    public void checkParameterForMockHashedPassword() throws JsonProcessingException{

        String username = loginResponse.getUser().getUsername();
        String requestBody = requestBodyLoader(validUsernameAndHashedPassword);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        // Username check
        if (jsonNode.get("username").asText().equals(username)) {
            logger.info("The username in the response matches the one in the request");
            Allure.addAttachment("username check","The username in the response matches the one in the request");
        } else {
            logger.error("The username in the response does not match the one in the request");
            Allure.addAttachment("username check","The username in the response does not matches the one in the request");
            Assertions.fail("The username in the response does not match the one in the request");
        }
    }
    @Then("Save the access token for Mock Service by using hashed password")
    public void saveAccessTokenForMockHashedPassword(){

        accessToken = loginResponse.getTokenDetails().getAccessToken();
        logger.info("The Access Token has been saved: " + accessToken);
        Allure.addAttachment("Access Token", accessToken);

        loginMockService.stopMockServer();
    }

    @Given("User logs into the system with a valid username and hashed password for Mock Service, true query")
    public void validUsernameAndHashedPasswordTrueQuery(){

        loginMockService = new LoginMockService();
        loginMockService.startMockServer();
        loginMockService.setupLoginMock(NOT_OK, mockResponseBodyInvalid, validUsernameAndHashedPassword, "true");

        loginMockTests = new LoginMockTest();  // Create the LoginMockTest class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameAndHashedPassword,NOT_OK,true);  // Save the response in loginResponse

        logger.info("The system has been logged in with a valid username and password");

        loginMockService.stopMockServer();
    }
}
