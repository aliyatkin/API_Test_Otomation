Feature: User Renew Access Token
  As a user, I want to log into the system with different credentials and validate the responses. Then I want to renew the access token.

  Scenario Outline: User logs into the system, then refresh the access token
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user refresh the access token with the saved access token
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system, then tries to refresh the access token, verifies error response
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user can not refresh the access token with empty parameter
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system, then refresh the access token, verifies error response
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user can not refresh the access token with wrong parameter
    Examples:
      | username | password |
      | aselsan  | aselsan  |