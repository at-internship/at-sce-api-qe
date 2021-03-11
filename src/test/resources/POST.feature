@Post

Feature: Send users values to create a user in the DB

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    And I have access to "at-sce-db" database

  @US_018 @1
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
    And Information retrieved from service should match with DB collection "users"

  @2
  Scenario: Send a type data in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 3                     |
      | firstName | Juan                  |
      | lastName  | Lopez                 |
      | email     | juan@agilethought.com |
      | password  | JuLop98               |
      | status    | 1                     |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And  Information retrieved from service should match with DB collection "users"

  @3
  Scenario: Send a null in the type field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body with type or status as null:
      | type      |                              |
      | firstName | Migue                        |
      | lastName  | Olmos                        |
      | email     | miguelolmos@agilethought.com |
      | password  | 0lm0sM1gu3                   |
      | status    | 1                            |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And  Information retrieved from service should match with DB collection "users"

  @4
  Scenario: Send a firstName data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 2                       |
      | firstName | 5                       |
      | lastName  | Cernas                  |
      | email     | cer555@agilethought.com |
      | password  | Cernas5123              |
      | status    | 0                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And Information retrieved from service should match with DB collection "users"

  @5
  Scenario: Send a null in the type field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 3                       |
      | firstName |                         |
      | lastName  | Doriga                  |
      | email     | doriga@agilethought.com |
      | password  | doriganuNU              |
      | status    | 0                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And  Information retrieved from service should match with DB collection "users"

  @6
  Scenario: Send a lastName data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 2                         |
      | firstName | Azul                      |
      | lastName  | 84                        |
      | email     | azul8844@agilethought.com |
      | password  | azulita9898               |
      | status    | 0                         |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And  Information retrieved from service should match with DB collection "users"

  @7
  Scenario: Send a null in the lastName field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 1                          |
      | firstName | Ana                        |
      | lastName  |                            |
      | email     | anamadrid@agilethought.com |
      | password  | ana88                      |
      | status    | 1                          |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And  Information retrieved from service should match with DB collection "users"

  @8
  Scenario: Send a email data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 24           |
      | firstName | Manuel       |
      | lastName  | Navarro      |
      | email     | 7777         |
      | password  | n4vr00M4Nu31 |
      | status    | 1            |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And  Information retrieved from service should match with DB collection "users"

  @9
  Scenario: Send a null in the email field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 8        |
      | firstName | Bryan    |
      | lastName  | Garza    |
      | email     |          |
      | password  | Garza123 |
      | status    | 0        |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And  Information retrieved from service should match with DB collection "users"

  @10
  Scenario: Send a password data not in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 4                       |
      | firstName | Ruben                   |
      | lastName  | Cuevas                  |
      | email     | cuevas@agilethought.com |
      | password  | 888779988               |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And  Information retrieved from service should match with DB collection "users"

  @11
  Scenario: Send a null in the password field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 1                       |
      | firstName | Amelia                  |
      | lastName  | Escareno                |
      | email     | amelia@agilethought.com |
      | password  |                         |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from service should match with DB collection "users"

  @12
  Scenario: Send a status data in string format with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body:
      | type      | 6                       |
      | firstName | Carlos                  |
      | lastName  | Espindola               |
      | email     | carlos@agilethought.com |
      | password  | CarEsp                  |
      | status    | 1                       |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "400"
    And  Information retrieved from service should match with DB collection "users"

  @13
  Scenario: Send a null in the status field with all the other data in the other fields being correct
    Given I have the following information for new user and build a request body with type or status as null:
      | type      | 5                       |
      | firstName | Karina                  |
      | lastName  | Delgado                 |
      | email     | kardel@agilethought.com |
      | password  | K4r1N4123               |
      | status    |                         |
    And   I am targeting endpoint for "create_users"
    When  I send a POST request
    Then  The status code should be "201"
    And Information retrieved from service should match with DB collection "users"