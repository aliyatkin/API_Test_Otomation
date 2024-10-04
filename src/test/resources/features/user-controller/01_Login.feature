Feature: User Login
  As a user, I want to log into the system with different credentials and validate the responses.

  Scenario: User attempts to log in with a valid username and an invalid password
    Given the user logs in with a valid username and an invalid password

  Scenario: User attempts to log in with a valid username and an empty password
    Given the user logs in with a valid username and an empty password

  Scenario: User successfully logs in with a valid username and password
    Given the user logs in with a valid username and password
    When the system verifies the required response parameters
    Then the access token is saved

  Scenario: User successfully logs in with a valid username and hashed password
    Given the user logs in with a valid username and a hashed password
    When the system verifies the required response parameters using the hashed password
    Then the access token is saved using the hashed password

  Scenario: User attempts to log in with a valid username, hashed password, and a true query
    Given the user logs in with a valid username and a hashed password with a true query
