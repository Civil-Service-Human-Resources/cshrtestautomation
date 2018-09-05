Feature: In order to apply for the job I want to view and understand full job description

  Scenario: When I view an invalid bookmarked link I should see error if not found
  Given I clicked on a bookmarked link
  And the closingdate is in the past
  Then I should be shown an error message "Cannot view job"
  And link to "search for jobs"

  Scenario:Multiple comma separated locations are displayed for a job when the jobs exists at more than one location
  Given  When I enter "<keyword>" in job title and "<locationkeyword>" in location
  And I click on search
  When there are multiple locations associated with the job
  Then I see multiple locations against the job

  Scenario: I see all titles in the job description
  When I enter "<keyword>" in job title and "<locationkeyword>" in location
  And I click on search
  When I open the full job description
  Then I see in key facts 'Working pattern', 'Contract type', 'what we offer','salary band','grade','location'
  And I see 'description', 'What we offer', 'Selection process','Nationality requirements'

 Scenario Outline:
  Given I entered an "<invalidurl>"
  Then I should see an error "This page cannot be found"
  Examples: :
   |invalidurl|
   |http://localhost:3000/dkjfnbwbfhwbfvresults?jkeyword=&location=london|

  Scenario Outline: Errors messages

    Given that I am attempting to access a page/resource

    And there is a <httpStatus> <error>

    When I request the page/resource

    Then I see an error message page

    And header: <header>

    And message: <message>

    Examples:
  |httpStatus|	error|	header|	message|
  |400       |	Bad Request|	See attachment|	See attachment|
  |401|	Unauthorised      |	See attachment|	See attachment|
  |403|	Forbidden         |	See attachment|	See attachment|
  |404|	Page not found    |	See attachment|	See attachment|
  |500|	Bad gateway       |	See attachment|	See attachment|
  |502|	Service           | unavailable	See attachment|	See attachment|
  |503|	Gateway           | timeout	See attachment    |	See attachment|

  Scenario: I can see location details on the job advert

    Given that I am on the job advert

    When I look at the key facts information

    Then I can see the location(s) linked to the job