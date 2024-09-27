Feature: User Logout

  Scenario: User logs into the system, then logs out with a valid Access Token.
    Given User logs into the system with a valid username and password for logout tests
    When Verifying the required response parameters for logout tests
    Then Save the access token for logout tests
    And User logs out from the system with the saved access token