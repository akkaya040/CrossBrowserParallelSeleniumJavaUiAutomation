@language:gherkin
Feature: Insider Home Page

  As a user
  I want to visit the Insider home page
  So that I can verify the page loads correctly with all main sections

  @smoke @homepage @C213111
  Scenario: Testcase C213111 Verify Insider Home page Loaded Successfully
    Given I navigate to "https://insiderone.com/"
    Then the Insider home page should be opened
    And the main blocks on the homepage should be visible
    And wait for 45 seconds

  @smoke @homepage @C213112
  Scenario: Testcase C213112 Verify Insider Home page Loaded Successfully
    Given I navigate to "https://insiderone.com/"
    Then the Insider home page should be opened
    And the main blocks on the homepage should be invisible
    And wait for 55 seconds
