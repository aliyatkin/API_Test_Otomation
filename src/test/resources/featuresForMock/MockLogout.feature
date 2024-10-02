Feature: User Logout with Mock Service

  Scenario: User logs into the system, then logs out with a valid Access Token
    Given User logs into the system with a valid username and password for Mock Service
    When Verifying the required response parameters for Mock Service
    Then Save the access token for Mock Service
    And User logs out from the system with the saved access token, Mock Service