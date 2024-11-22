Feature: User Logout
  As a user, I want to log into the system with different credentials and validate the responses. Then I want to logs out from the system.

  Scenario Outline: User logs into the system, then logs out with a valid Access Token.
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user logs out from the system

    Examples:
      | username | password |
      | aselsan  | aselsan  |