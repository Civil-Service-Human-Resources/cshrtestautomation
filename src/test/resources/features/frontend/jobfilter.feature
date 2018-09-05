Feature: As a candidate I want to fitler jobs based on salary

  Background:
    Given I open cshr website
    Then I should see homepage with options to search for location and keyword
    #The data should be prepared in so many ways to prove this such as jobs that have no max salary
    Scenario Outline:Filter jobs based on salary band and verify the jobs with the appropriate salary is displayed
      When I enter "<keyword>" in job title and "<locationkeyword>" in location
      And I click on search
      When I filter jobs based on salary bands "<minSal>" and "<maxSal>"
      And I click on search
   #  Then I should see only the results matching "<keyword>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>" or "<regions>" and salary in <minSal> and <maxSal> or overseas locations in a new page

      Examples:
    |keyword         |locationkeyword|minSal       |maxSal  |comment|
    |                |    london     | £10,000     |        | should see all jobs equal to and greater than 10000|
    |                |    london     | £10,000     |£20,000 | should see all jobs equal to and between 10000 and 20000|

    Scenario Outline:Verify smart salary filter works
     When I enter "<keyword>" in job title and "<locationkeyword>" in location
     And I click on search
     When I filter jobs based on salary bands "<minSal>" and "<maxSal>"
     Then The salary "<salType>" starts at "<nextVal>"
    Examples:
      |keyword         |locationkeyword |minSal     |maxSal |nextVal  |salType|comment|
      |business analyst|    london     | £10,000   |        |£20,000  | max   |should see all jobs equal to and greater than 10000|
     # |business analyst|    london     |           |£50,000 |£40,000  | min    |should see all jobs equal to and between 10000 and 20000|
