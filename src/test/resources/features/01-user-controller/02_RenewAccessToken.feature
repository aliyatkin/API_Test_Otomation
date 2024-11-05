Feature: Renew Access Token

  Scenario Outline: User logs into the system, then refresh the access token
    Given the user logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user refresh the access token with the saved access token
    Then the user logs out from the system
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system, then refresh the access token, verifies error response
    Given the user logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user can not refresh the access token with empty parameter
    Then the user logs out from the system
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User logs into the system, then refresh the access token, verifies error response
    Given the user logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user can not refresh the access token with wrong parameter
    Then the user logs out from the system
    Examples:
      | username | password |
      | aselsan  | aselsan  |