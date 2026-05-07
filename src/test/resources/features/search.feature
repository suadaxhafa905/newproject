@Search
Feature: Search functionality

  Scenario: User searches for a product

    Given User is on search page
    When User searches for "Laptop"
    Then Search results should be displayed