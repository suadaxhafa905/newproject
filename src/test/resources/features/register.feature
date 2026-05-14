@UI
@Register

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



  @RegisterNegativess
  Scenario: User enters invalid register data

    Given User is on register page
    When User enters username ""
    And User enters email "wrongemail"
    And User enters password "123456"
    And User enters confirm password "999999"
    And User clicks create account button
    Then Error messages should be displayed



  @Smoke @Regression @Negative
  Scenario Outline: Register validation tests

    Given User is on register page
    When User fills register form with test data "<testCase>"
    And User clicks create account button
    Then Register error message should be displayed for test data "<testCase>"

    Examples:
      | testCase |
      | TC_REGISTER_001 |
      | TC_REGISTER_002 |
      | TC_REGISTER_003 |
      | TC_REGISTER_004 |
      | TC_REGISTER_005 |
      | TC_REGISTER_006 |
      | TC_REGISTER_007 |
      | TC_REGISTER_008 |
      | TC_REGISTER_009 |
      | TC_REGISTER_010 |
      | TC_REGISTER_011 |
