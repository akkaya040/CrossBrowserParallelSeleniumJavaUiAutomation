@language:gherkin
Feature: Insider Home Page

  A user
  want to visit the Insider home page
  then verify the page loads correctly with all main sections
  and verify filters and redirection to the application page

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

  @regression @careerpage @C213113
  Scenario: Testcase C213113 Verify Location And Team Filters
    Given Navigate : to "https://insiderone.com/careers/"
    When Check : "Decline" cookies
    Then Verify : the Insider careers page should be opened
    And Click : explore open roles button
    Then Verify : open role cards
    When Click : open positions on the role "Quality Assurance"
    Then Verify : lever jobs page should be opened
    And Select : lever job filters
      | Location Type | Location          | Team              | Work Type |
      |               | Istanbul, Turkiye | Quality Assurance |           |
    Then Verify : lever filtered jobs contains "Istanbul, Turkiye"
    Then Verify : lever filtered jobs contains "Quality Assurance"

  @regression @careerpage @C213114
  Scenario: Testcase C213114 Verify Lever Application Redirection
    Given Navigate : to "https://insiderone.com/careers/"
    When Check : "Decline" cookies
    Then Verify : the Insider careers page should be opened
    And Click : explore open roles button
    Then Verify : open role cards
    When Click : open positions on the role "Quality Assurance"
    Then Verify : lever jobs page should be opened
    And Select : lever job filters
      | Location Type | Location          | Team              | Work Type |
      |               | Istanbul, Turkiye | Quality Assurance |           |
    Then Verify : lever filtered jobs contains "Istanbul, Turkiye"
    Then Verify : lever filtered jobs contains "Quality Assurance"
    And Click : apply first first job
    Then Verify : page forwarded to the application page

