@Get

Feature: Get the information of all registered users

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database

  @US_016 @1
  Scenario: Get all registered users in the DB with the correct information when the number of users is > 0
  Given I want to retrieve all users
  When I send a GET request
  Then the status code should be "200"
  And the number of registered users should be the same of the number of retrieved users
  And information retrieved from service should match with DB
  @US_016 @2
  Scenario: Get an empty list when there aren't registered users in the DB
    Given I want to retrieve 0 registered users
    When I send a GET request
    Then the status code should be "200"
    And the number of registered users should be 0
    And information retrieved from service should be an empty list

