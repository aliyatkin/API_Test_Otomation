Feature: User Logout with Mock Service

  Scenario Outline: User logs into the system, then logs out with a valid Access Token
    Given the user logs in with a valid "<username>" and "<password>" using the mock service
    When the system verifies the required response parameters for the mock service
    Then the access token is saved for the mock service
    And the user logs out from the system with the saved access token for mock service
    Examples:
      | username | password   |
      | aselsan  | aselsan    |