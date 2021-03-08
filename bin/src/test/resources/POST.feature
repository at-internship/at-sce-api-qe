@Post

Feature: Send users values to create a user in the DB

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service

  @US_018
  @1
  Scenario: Send all correct data to create a User
    Given I have the following information for new user and build a request body:
      | type      | 5                      |
      | firstName | Mario                  |
      | lastName  | Ahumada                |
      | email     | mario@agilethought.com |
      | password  | computer123            |
      | status    | 1                      |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"

  @2
  Scenario: Send a type data in string format with all the other datain the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | "5"                     |
      | firstName | Juan                  |
      | lastName  | Lopez                  |
      | email     | juan@agilethought.com |
      | password  | JuLop98                  |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"

  @3
  Scenario: Send a null in the type field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | null                     |
      | firstName | Migue                  |
      | lastName  | Olmos                  |
      | email     | miguelolmos@agilethought.com |
      | password  | 0lm0sM1gu3                  |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
  @4
  Scenario: Send all correct data to create a User
    Given I have the following information for new user and build a request body:
      | type      | null                     |
      | firstName | Migue                  |
      | lastName  | Olmos                  |
      | email     | miguelolmos@agilethought.com |
      | password  | 0lm0sM1gu3                  |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"