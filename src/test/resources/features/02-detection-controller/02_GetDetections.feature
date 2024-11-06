Feature: Get Detections

  Scenario Outline: User logs into the system, then refresh the access token
    Given the user logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |
      |  aselsan | aselsan   |  1   |     10   |