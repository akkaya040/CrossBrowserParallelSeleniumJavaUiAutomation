@language:gherkin
Feature: Insider Home Page

  A user
  want to visit the Insider home page
  then verify the page loads correctly with all main sections

  @smoke @homepage @C213111
  Scenario: Testcase C213111 Verify Insider Home page Loaded Successfully
    Given Navigate : to ""
    When Check : "Accept" cookies
    Then Verify : the Insider home page should be opened
    And Verify : the main blocks on the homepage should be visible

  @smoke @homepage @C213112
  Scenario: Testcase C213112 Verify Insider Home page Loaded Fail
    Given Navigate : to "https://insiderone.com/"
    When Check : "Decline" cookies
    Then Verify : the Insider home page should be opened
    And fail test on this step


  @smoke @homepage @C213113
  Scenario: Testcase C213113 Verify Insider Careers Page
    Given Navigate : to "https://insiderone.com/careers/"
    When Check : "Decline" cookies
    Then Verify : the Insider careers page should be opened
    #And Click :

