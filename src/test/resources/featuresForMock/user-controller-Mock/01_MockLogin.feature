Feature: User Login with Mock Service
  As a user, I want to log into the system with different credentials using the mock service and validate the responses.

  Scenario: User logs in with a valid username and an invalid password, verifies error response
    Given the user logs in with a valid username and an invalid password using the mock service

  Scenario: User logs in with a valid username and an empty password, verifies error response
    Given the user logs in with a valid username and an empty password using the mock service

  Scenario: User logs in with a valid username and password, verifies response parameters, and saves the access token
    Given the user logs in with a valid username and password using the mock service
    When the system verifies the required response parameters for the mock service
    Then the access token is saved for the mock service

  Scenario: User logs in with a valid username and hashed password, verifies response parameters, and saves the access token
    Given the user logs in with a valid username and hashed password using the mock service
    When the system verifies the required response parameters using the hashed password for the mock service
    Then the access token is saved using the hashed password for the mock service

  Scenario: User logs in with a valid username and hashed password, true query, verifies error response
    Given the user logs in with a valid username and hashed password with a true query using the mock service
