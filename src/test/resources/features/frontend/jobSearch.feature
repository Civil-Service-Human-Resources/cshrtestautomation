Feature: Search Results
  As a job seeker
  In order to find a role relevant to me
  I want to be able to search on a keyword and location

  Background:
    Given I open cshr website
    Then I should see homepage with options to search for location and keyword

  Scenario Outline: Verify that the keyword and location are both optional on the results page
    When I enter either keyword "<keyword>" or location "<locationkeyword>"
    Then I should see all the results matching "<keyword>" in a new page
    When I enter "<keyword>" as keyword or "<location>" location on the search results page
    And I click update results
    Then I should see all the results matching "<keyword>" in a new page

    Examples:
      |keyword        |locationkeyword|
      |  driver       | bristol       |
      |               | bs1           |
      |  driver       | bs1           |

   Scenario Outline: Verify that the keyword and location are both optional on the homepage
             When I enter either keyword "<keyword>" or location "<locationkeyword>"
             And I click on search
             Then I should see search results
    Examples:
    |keyword         |locationkeyword|
    |director        |     bristol   |
    |               |               |
    |  director     |               |

  #CSHRCP-207
  Scenario Outline: Search for valid criteria in both keyword and location and verify jobs displayed are in 30 mile radius
    When I enter keyword in job title and locationKeyword in location
    And I click on search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>" or "<regions>" or "<overseas>" locations in a new page
    And total number of jobs matching search
    And partial job description
    And number of vaccancies, location, salary, job grade, closing date
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
    When I enter "<newKeyword>" in the keyword
    And I click update results
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

