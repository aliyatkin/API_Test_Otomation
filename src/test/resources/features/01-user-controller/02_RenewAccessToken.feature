Feature: User Renew Access Token
  As a user, I want to log into the system with different credentials, validate the responses, and renew the access token.

  Scenario Outline: User logs into the system, then refresh the access token
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user refreshes the access token using the saved access token
    Then the access token is refreshed and saved
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system, then tries to refresh the access token, verifies error response
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user tries to refresh the access token with an empty parameter
    Then the access token is not refreshed and an error message is displayed
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system, then tries to refresh the access token, verifies error response
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user tries to refresh the access token with an incorrect parameter
    Then the access token is not refreshed, and an error message is displayed
    Examples:
      | username | password |
      | aselsan  | aselsan  |