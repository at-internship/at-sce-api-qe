@Get

Feature: Get operation

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database


@US_025 @1
  Scenario: Get the histories of a user
  Given I want to retrieve the histories that correspond to this id "100"
  And I am targeting endpoint for "get_histories"
  When I send a GET request to get the histories
  Then The status code should be "200"
  And The number of registered histories should be the same of retrieved histories
  And Information retrieved from service should match with DB collection histories

@US_025 @2
Scenario: Get the histories of a user
  Given I want to retrieve the histories that correspond to this id "604f8e2dac1a413c8aba77a5"
  And I am targeting endpoint for "get_histories"
  When I send a GET request to get the histories
  Then The status code should be "200"
  And The number of registered histories should be 0
  And Information retrieved from get histories should be an empty list