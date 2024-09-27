Feature: User Login with Mock Server

  Scenario: User logs into the system with a valid username and invalid password, verifies error response
    Given User logs into the system with a valid username and invalid password for Mock Service

  Scenario: User logs into the system with a valid username and empty password, verifies error response
    Given User logs into the system with a valid username and empty password for Mock Service

  Scenario: User logs into the system with a valid username and password, verifies response parameters, and saves the access token.
    Given User logs into the system with a valid username and password for Mock Service
    When Verifying the required response parameters for Mock Service
    Then Save the access token for Mock Service

  Scenario:  User logs into the system with a valid username and hashed password, verifies response parameters, and saves the access token
    Given User logs into the system with a valid username and hashed password for Mock Service
    When Verifying the required response parameters for Mock Service by using hashed password
    Then Save the access token for Mock Service by using hashed password

  Scenario:  User logs into the system with a valid username and hashed password, true query, verifies error response
    Given User logs into the system with a valid username and hashed password for Mock Service, true query

