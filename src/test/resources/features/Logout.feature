Feature: User Logout

  Scenario: User logs into the system, then logs out with a valid Access Token.
    Given User logs into the system with a valid username and password
    When Verifying the required response parameters
    Then Save the access token
    And User logs out from the system with the saved access token