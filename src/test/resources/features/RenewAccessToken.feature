Feature: Renew Access Token

  Scenario: User logs into the system, then refresh the access token
    Given User logs into the system with a valid username and password for Renew Access Token
    When Verifying the required response parameters for Renew Access Token
    Then Save the access token for Renew Access Token
    And User refresh the access token with the saved access token

  Scenario: User logs into the system, then refresh the access token, verifies error response
    Given User logs into the system with a valid username and password for Renew Access Token
    When Verifying the required response parameters for Renew Access Token
    Then Save the access token for Renew Access Token
    And User can not refresh the access token with empty parameter