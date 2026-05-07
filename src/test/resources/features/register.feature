
Feature: Register functionality
  @Register
  Scenario: User creates a new account
    Given User is on register page
    When User enters username "suada123"
    And User enters email "suada@test.com"
    And User enters password "123456"
    And User enters confirm password "123456"
    And User clicks create account button
    Then Account should be created successfully



  @RegisterNegative
  Scenario: User enters invalid register data

    Given User is on register page
    When User enters username ""
    And User enters email "wrongemail"
    And User enters password "123456"
    And User enters confirm password "999999"
    And User clicks create account button
    Then Error messages should be displayed