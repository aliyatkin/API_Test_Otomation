Feature: Renew Access Token for Mock Service

  Scenario: User logs into the system, then refresh the access token, Mock Service
    Given User logs into the system with a valid username and password for Mock Service
    When Verifying the required response parameters for Mock Service
    Then Save the access token for Mock Service
    And User refresh the access token with the saved access token, Mock Service
    And User logs out from the system with the saved access token, Mock Service

  Scenario: User logs into the system, then refresh the access token, verifies error response, Mock Service
    Given User logs into the system with a valid username and password for Mock Service
    When Verifying the required response parameters for Mock Service
    Then Save the access token for Mock Service
    And User can not refresh the access token with empty parameter, Mock Service
    And User logs out from the system with the saved access token, Mock Service

  Scenario: User logs into the system, then refresh the access token, verifies error response, Mock Service
    Given User logs into the system with a valid username and password for Mock Service
    When Verifying the required response parameters for Mock Service
    Then Save the access token for Mock Service
    And User can not refresh the access token with wrong parameter, Mock Service
    And User logs out from the system with the saved access token, Mock Service