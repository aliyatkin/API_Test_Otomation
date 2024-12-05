Feature: User Logout
  As a user, I want to log into the system with different credentials, validate the responses, and log out of the system.

  Scenario Outline: User logs into the system and logs out with a valid access token
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user logs out of the system
    Then the system successfully logs the user out

    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system and tries to log out with an invalid token
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user tries to log out of the system with an invalid token
    Then the system fails to log the user out

    Examples:
      | username | password |
      | aselsan  | aselsan  |