Feature: Renew Access Token

  Scenario: User logs into the system, then refresh the access token
    Given User logs into the system with a valid username and password
    When Verifying the required response parameters
    Then Save the access token
    And User refresh the access token with the saved access token
    And User logs out from the system with the saved access token

  Scenario: User logs into the system, then refresh the access token, verifies error response
    Given User logs into the system with a valid username and password
    When Verifying the required response parameters
    Then Save the access token
    And User can not refresh the access token with empty parameter
    And User logs out from the system with the saved access token

  Scenario: User logs into the system, then refresh the access token, verifies error response
    Given User logs into the system with a valid username and password
    When Verifying the required response parameters
    Then Save the access token
    And User can not refresh the access token with wrong parameter
    And User logs out from the system with the saved access token