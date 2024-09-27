package com.apiTests.serviceSteps.mockTestSteps;

import com.apiTests.mockService.LoginMockService;
import com.apiTests.models.user_controller.login.LoginResponse;
import com.apiTests.requests.mockRequests.LoginMockTest;
import com.apiTests.requests.serviceRequests.user_controller.LoginTests;
import com.apiTests.serviceSteps.serviceSteps.LoginSteps;
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

    private LoginResponse loginResponse;
    private String accessToken;
    private LoginMockTest loginMockTests;
    private static LoginMockService loginMockTest;
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    @Given("User logs into the system with a valid username and invalid password for Mock Service")
    public void validUsernameAndInvalidPasswordForMock(){

        String mockRespBodyPath = mockResponseBodyInvalid;
        String mockReqBodyPath = validUsernameInvalidPassword;

        loginMockTest = new LoginMockService();
        loginMockTest.startMockServer();  // Mock sunucuyu başlat
        loginMockTest.setupLoginMock(NOT_OK_404, mockRespBodyPath, mockReqBodyPath, "true");   // Mock yanıtını ayarla

        // Read the data from JSON file and save it in requestBody
        loginMockTests = new LoginMockTest();  // Create the LoginTests class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameInvalidPassword,NOT_OK_404,true);  // Save the response in loginResponse

        logger.info("The system has been logged in with a valid username and password");

        loginMockTest.stopMockServer();  // Mock sunucuyu durdur
    }
    @Given("User logs into the system with a valid username and empty password for Mock Service")
    public void validUsernameAndEmptyPasswordForMock(){

        String mockRespBodyPath = mockResponseBodyEmpty;
        String mockReqBodyPath = validUsernameAndEmptyPassword;

        loginMockTest = new LoginMockService();
        loginMockTest.startMockServer();  // Mock sunucuyu başlat
        loginMockTest.setupLoginMock(NOT_OK_404,mockRespBodyPath,mockReqBodyPath, "true");   // Mock yanıtını ayarla

        // Read the data from JSON file and save it in requestBody
        loginMockTests = new LoginMockTest();  // Create the LoginTests class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameAndEmptyPassword,NOT_OK_404,true);  // Save the response in loginResponse

        logger.info("The system has not been logged in with a valid username and password");

        loginMockTest.stopMockServer();  // Mock sunucuyu durdur
    }

    @Given("User logs into the system with a valid username and password for Mock Service")
    public void validUsernameAndPasswordForMock() {

        String mockRespBodyPath = mockResponseBody;
        String mockReqBodyPath = validUsernameAndPassword;

        loginMockTest = new LoginMockService();
        loginMockTest.startMockServer();  // Mock sunucuyu başlat
        loginMockTest.setupLoginMock(OK, mockRespBodyPath, mockReqBodyPath, "true");   // Mock yanıtını ayarla

        // Read the data from JSON file and save it in requestBody
        loginMockTests = new LoginMockTest();  // Create the LoginTests class for Login
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

        loginMockTest.stopMockServer();  // Mock sunucuyu durdur
    }

    @Given("User logs into the system with a valid username and hashed password for Mock Service")
    public void validUsernameAndHashedPassword(){

        String mockRespBodyPath = mockResponseBody;
        String mockReqBodyPath = validUsernameAndHashedPassword;

        loginMockTest = new LoginMockService();
        loginMockTest.startMockServer();  // Mock sunucuyu başlat
        loginMockTest.setupLoginMock(OK, mockRespBodyPath, mockReqBodyPath,"false");   // Mock yanıtını ayarla

        // Read the data from JSON file and save it in requestBody
        loginMockTests = new LoginMockTest();  // Create the LoginTests class for Login
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

        loginMockTest.stopMockServer();  // Mock sunucuyu durdur
    }

    @Given("User logs into the system with a valid username and hashed password for Mock Service, true query")
    public void validUsernameAndHashedPasswordTrueQuery(){

        String mockRespBodyPath = mockResponseBodyInvalid;
        String mockReqBodyPath = validUsernameAndHashedPassword;

        loginMockTest = new LoginMockService();
        loginMockTest.startMockServer();  // Mock sunucuyu başlat
        loginMockTest.setupLoginMock(NOT_OK_404, mockRespBodyPath, mockReqBodyPath, "true");   // Mock yanıtını ayarla

        // Read the data from JSON file and save it in requestBody
        loginMockTests = new LoginMockTest();  // Create the LoginTests class for Login
        loginResponse = loginMockTests.LoginForMock(validUsernameAndHashedPassword,NOT_OK_404,true);  // Save the response in loginResponse

        logger.info("The system has been logged in with a valid username and password");

        loginMockTest.stopMockServer();  // Mock sunucuyu durdur
    }
}
