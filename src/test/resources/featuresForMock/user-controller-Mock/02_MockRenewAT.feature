Feature: Renew Access Token for Mock Service

  Scenario: User logs into the system, then refresh the access token, Mock Service
    Given the user logs in with a valid username and password using the mock service
    When the system verifies the required response parameters for the mock service
    Then the access token is saved for the mock service
    And the user refresh the access token with the saved access token for mock service
    And the user logs out from the system with the saved access token for mock service

  Scenario: User logs into the system, then refresh the access token, verifies error response, Mock Service
    Given the user logs in with a valid username and password using the mock service
    When the system verifies the required response parameters for the mock service
    Then the access token is saved for the mock service
    And the user can not refresh the access token with empty parameter for mock service
    And the user logs out from the system with the saved access token for mock service

  Scenario: User logs into the system, then refresh the access token, verifies error response, Mock Service
    Given the user logs in with a valid username and password using the mock service
    When the system verifies the required response parameters for the mock service
    Then the access token is saved for the mock service
    And the user can not refresh the access token with wrong parameter, mock service
    And the user logs out from the system with the saved access token for mock service