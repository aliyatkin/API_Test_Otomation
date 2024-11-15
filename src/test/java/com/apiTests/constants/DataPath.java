package com.apiTests.constants;

public class DataPath {

    // Paths of JSON files that store Login information

    public static final String V_USERNAME_PASSWORD = "src/test/resources/jsonFilesLogin/validUsernameAndPassword.json";         // valid username and valid password
    public static final String V_USERNAME_I_PASSWORD = "src/test/resources/jsonFilesLogin/validUsernameInvalidPassword.json";   // valid username and invalid password
    public static final String V_USERNAME_E_PASSWORD = "src/test/resources/jsonFilesLogin/validUsernameAndEmptyPassword.json";  // valid username and empty password
    public static final String V_USERNAME_H_PASSWORD = "src/test/resources/jsonFilesLogin/validUsernameAndHashedPassword.json"; // valid username and hashed password
    public static final String HASHED_PASSWORD = "src/test/resources/txtFiles/hashedPassword.txt";                              // hashed password

    // Paths of JSON and .txt files that store accessToken information

    public static final String ACCESS_TOKEN = "src/test/resources/txtFiles/accessToken.txt";                                    // Saved example access token to use in
    public final static String ACCESS_TOKEN_JSON = "src/test/resources/jsonFilesLogin/accessToken.json";                        // Saved example access token to use in Mock Service

    // Paths of JSON files that store information about Mock Service

    public static final String MOCK_LOGIN_RESPONSE_BODY = "src/test/resources/jsonFilesLogin/mockFiles/mockLoginResponse.json";     // Prepared in advance Mock Service Login Response
    public static final String MOCK_LOGOUT_RESPONSE_BODY = "src/test/resources/jsonFilesLogin/mockFiles/mockLogoutResponse.json";   // Prepared in advance Mock Service Logout Response
    public static final String MOCK_RENEW_RESPONSE_BODY = "src/test/resources/jsonFilesLogin/mockFiles/mockRenewResponse.json";     // Prepared in advance Mock Service Renew Access Token Response

    // Paths of JSON files that store some information for fail scenarios

    public static final String NULL_JSON = "src/test/resources/jsonFilesLogin/null.json";                           // Filled as "null" JSON file
    public static final String EMPTY_JSON = "src/test/resources/jsonFilesLogin/empty.json";                         // No filled, empty JSON file
    public static final String WRONG_ACCESS_TOKEN = "src/test/resources/jsonFilesLogin/wrongAccessToken.json";      // Out of date access token to use in fail scenarios
}
