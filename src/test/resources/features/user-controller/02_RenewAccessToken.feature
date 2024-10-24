Feature: Renew Access Token

  Scenario Outline: User logs into the system, then refresh the access token
    Given the user logs in with a valid "<username>" and "<password>"
    When the system verifies the required response parameters
    Then the access token is saved
    And the user refresh the access token with the saved access token
    And the user logs out from the system with the saved access token
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system, then refresh the access token, verifies error response
    Given the user logs in with a valid "<username>" and "<password>"
    When the system verifies the required response parameters
    Then the access token is saved
    And the user can not refresh the access token with empty parameter
    And the user logs out from the system with the saved access token
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system, then refresh the access token, verifies error response
    Given the user logs in with a valid "<username>" and "<password>"
    When the system verifies the required response parameters
    Then the access token is saved
    And the user can not refresh the access token with wrong parameter
    And the user logs out from the system with the saved access token
    Examples:
      | username | password |
      | aselsan  | aselsan  |