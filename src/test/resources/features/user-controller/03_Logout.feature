Feature: User Logout

  Scenario: User logs into the system, then logs out with a valid Access Token.
    Given the user logs in with a valid username and password
    When the system verifies the required response parameters
    Then the access token is saved
    And the user logs out from the system with the saved access token