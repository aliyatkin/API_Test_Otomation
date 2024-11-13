Feature: User Login
  As a user, I want to log into the system with different credentials and validate the responses.

  Scenario Outline: User attempts to log in with a valid username and an invalid password
    When the user tries to logs in with a valid "<username>" and an invalid "<password>"
    Examples:
      | username | password   |
      | aselsan  | aselsan1   |
      | aselsan  | #VZZ3tX6*0 |
      | aselsan  | s@+m4rAsd* |
      | aselsan  | (6cvI%o&tH |
      | aselsan  | *i#LBnBt3A |

  Scenario Outline: User attempts to log in with a valid username and an empty password
    When the user tries to logs in with a valid "<username>" and an empty "<password>"
    Examples:
      | username | password |
      | aselsan  |          |

  Scenario Outline: User successfully logs in with a valid username and password
    When the user tries to logs in with a valid "<username>" and "<password>"
    Then the system verifies the required response parameters
    And the access token is saved
    Examples:
      | username | password |
      | aselsan  | aselsan  |

  Scenario Outline: User successfully logs in with a valid username and hashed password, and a false query
    When the user tries to logs in with a valid "<username>" and a hashed password with a "<trueOrFalse>" false query
    Then the system verifies the required response parameters using the hashed password
    And the access token is saved using the hashed password
    Examples:
      | username | trueOrFalse |
      | aselsan  |   false     |

  Scenario Outline: User attempts to log in with a valid username, hashed password, and a true query
    When the user tries to logs in with a valid "<username>" and a hashed password with a "<trueOrFalse>" true query
    Examples:
      | username | trueOrFalse |
      | aselsan  |   true      |