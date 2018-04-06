@intest
Feature: Search Results
  As a job seeker
  In order to find a role relevant to me
  I want to be able to search on a keyword and location

  Background:
    Given I open cshr website
    Then I should see homepage with options to search for location and keyword
   #CSHRCP-207
   Scenario Outline: Search with invalid criteria without a location and check if error is displayed both on the home page and results page
             When I enter "<keyword>" in job title and "<locationkeyword>" in location
             And I click on search
             Then I should see an error message "<message>"
             And I enter "<keyword>" in job title and "<newlocationkeyword>" in location
             And I click on search
             And I enter "<keyword>" in job title and "<locationkeyword>" in location
             And I click update results
             Then I should see an error message "<message>"
    Examples:
    |keyword         |locationkeyword|message|newlocationkeyword|
    |medical         |               |You need to enter a location|bristol|
    |                |               |You need to enter a location|london|

  #CSHRCP-207
  Scenario Outline: Search for valid criteria in both keyword and location and verify jobs displayed are in 30 mile radius and select a job displayed
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>" or "<regions>" or "<overseas>" locations in a new page
    And total number of jobs matching search
    And partial job description
    And number of vaccancies, location, salary, job grade, closing date
    When I select a job that matches my criteria
    Then I should be shown a full description of the job in a new page with salary min and max, closing date, Apply
    Examples:
      | keyword          | locationkeyword |latitude  |longitude |radius|regions             |overseas|
      |                  | london          |51.518043 |-0.109374 | 30   | greater london     |true|
      |  medical         | bath            |51.377971 | -2.356987| 30   | South West         |true|
      |                  | bs1             |51.449572 | -2.592711| 30   | South West         |true|

  #CSHRCP-207
  Scenario Outline: Search for valid criteria, check and click if back to search results link is displayed
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
    When I select a job that matches my criteria
    Then I should see back to search results
    And I click back to search results
    Examples:
      | keyword | locationkeyword |latitude|longitude|radius|
      | medical | london          |51.518043 |-0.109374| 30   |

  Scenario Outline: A no search results page is dispalyed when search doesn't match any results especially postcode or location is not valid
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
    Then A display results per page drop down is displayed "<isdisplayed>"
    Then I should see no results matching your search and link to navigate to home page
    When I click the link to try a new search
    Then I should see homepage with options to search for location and keyword
    Examples:
      | keyword | locationkeyword |isdisplayed|
      | dooooo  | sfjsjfjdf       |   false   |
      |         |        bh66     |   false   |

  Scenario Outline: An apply button is displayed on the job description page and clicking apply navigates to temporary page
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
    When I select a job that matches my criteria
    When I click the apply button
    #not implementing this temporary page
    Then I should see a temporary page

    Examples:
      | keyword | locationkeyword |latitude|longitude|radius|
      | web     | london          |51.518043 |-0.109374|  30  |

  Scenario: I should see Welsh language option on homepage
  Then I should see a link to welsh language

  Scenario Outline: User is able to edit and enter new keyword from the search results page to refine results
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
    And I should see search filters displayed in search results page
    And I clear existing search text in keyword
    When I enter "<newKeyword>" in the keyword
    And I click update results
  #  Then I should see only the results matching "<newKeyword>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>" in a new page
    Examples:
      | keyword | locationkeyword |newKeyword|latitude|longitude|radius|
      | web     | london          | technical|51.518043 |-0.109374| 30  |

  Scenario Outline: Pagination - Page number, next and prev links are displayed when the results are more than 10
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
  #  Then I should see only the results matching "<keyword>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>" in a new page
    And a display results per page drop down is displayed with "<default>" and in the list "<dopdownlist>"
    When the results are more than "<results to show>"
    And I scroll to the bottom of the page
    Then a link to next page is displayed
    When I click on next page
    And I scroll to the bottom of the page
    Then a link to previous page is displayed
    Examples:
  | keyword | locationkeyword |latitude |longitude|radius|default|dopdownlist|results to show|
  | web     | london          |51.518043|-0.109374|30    |10 per page| 5 per page,10 per page,25 per page,50 per page  |10 per page|

  Scenario Outline: Pagination - User can select number of results to display by selecting display results dropdown
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
    And a display results per page drop down is displayed with "<default>" and in the list "<dopdownlist>"
    And The user changes the number of results to display to "<results to show>"
    When the results are more than "<results to show>"
    And I scroll to the bottom of the page
    Then a link to next page is displayed
    When I click on next page
    And I scroll to the bottom of the page
    Then a link to previous page is displayed
    Examples:
      | keyword | locationkeyword |latitude |longitude|radius|default|dopdownlist|results to show|
      |  web    | london          |51.518043|-0.109374|30    |10 per page| 5 per page,10 per page,25 per page,50 per page  |50 per page|

    #CSHRCP-208, #CSHRCP-209
  #Scenario: A cookies page and privacy page are present
  #  Then I verify that privacy policy is present in the footer
  #  When I click privacy policy
  #  Then I should be taken to privacy policy page
  #  And  I verify that cookies link is present in the footer
  #  When I click cookies link
  #  Then I should be taken to cookies page

