package com.apiTests.constants;

public class Data_Path {

    // Paths of JSON files that store Login information

    public final static String V_USERNAME_PASSWORD = "src/test/resources/jsonFilesLogin/validUsernameAndPassword.json";         // valid username and valid password
    public final static String V_USERNAME_I_PASSWORD = "src/test/resources/jsonFilesLogin/validUsernameInvalidPassword.json";   // valid username and invalid password
    public final static String V_USERNAME_E_PASSWORD = "src/test/resources/jsonFilesLogin/validUsernameAndEmptyPassword.json";  // valid username and empty password
    public final static String V_USERNAME_H_PASSWORD = "src/test/resources/jsonFilesLogin/validUsernameAndHashedPassword.json"; // valid username and hashed password
    public final static String HASHED_PASSWORD = "src/test/resources/txtFiles/hashedPassword.txt";

    // Paths of JSON and .txt files that store accessToken information

    public final static String ACCESS_TOKEN = "src/test/resources/txtFiles/accessToken.txt";                                             // Saved example access token to use in
    public final static String ACCESS_TOKEN_JSON = "src/test/resources/jsonFilesLogin/accessToken.json";                        // Saved example access token to use in Mock Service

    // Paths of JSON files that store information about Mock Service

    public final static String MOCK_LOGIN_RESPONSE_BODY = "src/test/resources/jsonFilesLogin/mockFiles/mockLoginResponse.json"; // Prepared in advance Mock Service Login Response
    public final static String MOCK_LOGOUT_RESPONSE_BODY = "src/test/resources/jsonFilesLogin/mockFiles/mockLogoutResponse.json"; // Prepared in advance Mock Service Logout Response
    public final static String MOCK_RENEW_RESPONSE_BODY = "src/test/resources/jsonFilesLogin/mockFiles/mockRenewResponse.json"; // Prepared in advance Mock Service Renew Access Token Response

    // Paths of JSON files that store some information for fail scenarios

    public final static String NULL_JSON = "src/test/resources/jsonFilesLogin/null.json";                           // Filled as "null" JSON file
    public final static String EMPTY_JSON = "src/test/resources/jsonFilesLogin/empty.json";                         // No filled, empty JSON file
    public final static String WRONG_ACCESS_TOKEN = "src/test/resources/jsonFilesLogin/wrongAccessToken.json";      // Out of date access token to use in fail scenarios
}
