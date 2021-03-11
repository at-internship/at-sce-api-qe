@Post

Feature: POST operation for authenticate an user

  Background:
    Given I am working on "QA" environment
    And I am targeting "at-sce-api" service
    

  @US_015 @1
  Scenario: Authenticate a user with a register  email and register password in database
    Given I have the following information for  authenticate a user:
      | email     | ing.alexbolio@gmail.com |
      | password  | V4p0r30n                |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "200"
    And I have acces to the database "at-sce-db"
    


  @US_015 @2
  Scenario: Authenticate a user with a register  email and null password in database
    Given I have the following information for  authenticate a user:
      | email     | kevin@agilethouhgt.com |
      | password  |                        |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "200"
    And I have acces to the database "at-sce-db"
    
  @US_015 @3
  Scenario: Fail Authentication with a not register  email and a register password in database
    Given I have the following information for  authenticate a user:
      | email     | pruebas@gmail.com |
      | password  | V4p0r30n          |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
    And   There is not match with any value in DB "at-sce-db"

    
  @US_015 @4
  Scenario: Fail Authentication  with a not register  email and a not register password in database
    Given I have the following information for  authenticate a user:
      | email     | pruebas@gmail.com |
      | password  | pruebas           |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "401"
		And   There is not match with any value in DB "at-sce-db"

    
  @US_015 @5
  Scenario: Fail Authentication  with a null email and a null password
    Given I have the following information for  authenticate a user:
      | email     |  |
      | password  |  |
    And   I build my request body with information shown above  
    And   I am targeting endpoint for "authenticate_users"
    When  I send a POST request
    Then  The status code should be "500"


    