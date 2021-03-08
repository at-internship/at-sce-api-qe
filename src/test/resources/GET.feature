@Get

Feature: Get users information

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database


@US_001 @1
  Scenario: Get all users
  Given I want to retrieve all users
  When I send a GET request
  Then The status code should be "200"
  And Information retrieved from service should match with DB collection "users"