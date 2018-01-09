Feature: Search Results
  As a job seeker
  In order to find a role relevant to me
  I want to be able to search on a keyword and location

  Background:
    Given I open cshr website
    Then I should see homepage with options to search for location and keyword

#   Scenario Outline: Search with a keyword
#             When I enter "<keyword>" in job title and click search
#             Then I should see all the results matching "<keyword>" in a new page
#             And include the number of jobs found, partial job description, no of vaccancies, location, salary, job grade, closing date
#             When I select a job that matches my criteria
#             Then I should be shown a full description of the job in a new page with salary min and max, closing date
#    Examples:
#    |keyword|
#    |business analyst|
#    |analyst|
#    |Business Analyst|
#    |Analyst|

#    Scenario Outline: Search with location
#             When I enter "<locationkeyword>" in location and click search
#             Then I should see all the results matching "<locationkeyword>" in a new page
#             And include the number of jobs found, partial job description, no of vaccancies, location, salary, job grade, closing date
#             When I select a job that matches my criteria
#             Then I should be shown a full description of the job in a new page with salary min and max, closing date
 #   Examples:
#    |locationkeyword|
#    |bristol|


  Scenario Outline: Search for valid criteria in both keyword and location and select a job displayed
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    And total number of jobs matching search
    And partial job description
    And number of vaccancies, location, salary, job grade, closing date
    When I select a job that matches my criteria
    Then I should be shown a full description of the job in a new page with salary min and max, closing date, Apply
    Examples:
      | keyword          | locationkeyword |
      | web              | london          |
      | business analyst | bristol         |
      | Analyst          | Bristol         |
      | technical        | london          |


  Scenario Outline: Search for valid criteria, check and click if back to search results link is displayed
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    And I click back to search
    Then I should see homepage with options to search for location and keyword
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    When I select a job that matches my criteria
    Then I should see back to search results
    And I click back to search results
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    Examples:
      | keyword | locationkeyword |
      | web     | london          |

  Scenario Outline: A no search results page is dispalyed when search doesn't match any results
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see no results matching your search and link to navigate to home page
    When I click the link to try a new search
    Then I should see homepage with options to search for location and keyword
    Examples:
      | keyword | locationkeyword |
      | dooooo  | sfjsjfjdf       |

  Scenario Outline: An apply button is displayed on the job description page and clicking apply navigates to temporary page
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    When I select a job that matches my criteria
    When I click the apply button
    #not implementing this temporary page
    Then I should see a temporary page

    Examples:
      | keyword | locationkeyword |
      | web     | london          |

  Scenario: I should see Welsh language option on homepage
  Then I should see a link to welsh language

  Scenario Outline: User is able to edit and enter new keyword from the search results page to refine results
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    And I should see search filters displayed in search results page
    And I clear existing search text in keyword
    When I enter "<newKeyword>" in the keyword and refine
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    Examples:
      | keyword | locationkeyword |newKeyword|
      | web     | london          | technical|

  Scenario Outline: Pagination - Page number, next and prev links are displayed when the results are more than 20
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    When the results are more than "<results number>"
    Then a link to next page is displayed
    When I click on the link to the last page
    Then a previous link is displayed
    Examples:
  | keyword | locationkeyword |results number|
  | web     | london          | 20|

  Scenario Outline: Pagination - Only 5 pages are displayed any time
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    When the results are more than "<results number>"
    Then a link to next page is displayed
    When I click on the link to the last page
    Then I should see only "<noOfPagesListed>" pages listed
    And a previous link is displayed
    Examples:
      | keyword | locationkeyword |results number|noOfPagesListed|
      | l       | l               | 20           |     5         |

  Scenario Outline: Pagination - Next is not displayed when results are less than 20
    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
    Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
    When the results are less than "<results number>"
    Then a link to next page is not displayed
  Examples:
  | keyword | locationkeyword |results number|
  | technical| Bristol         | 20           |

  #Scenario Outline: An apply button is displayed on the job description page
  #  When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
  #  Then I should see only the results matching "<keyword>" in "<locationkeyword>" in a new page
  #  When I select a job that matches my criteria
  #  Then I should see contact point for applicants
  #  And working hours

#  Scenario Outline: Search for invalid criteria in both keyword and location to no results page displayed
#    When I enter "<keyword>" in job title and "<locationkeyword>" in location and click search
#   Then no jobs found is displayed
#    Examples:
#      |keyword|locationkeyword|
#      |web   |    london     |
#      |Lead   | Blackpool     |
#      |business analyst|bristol|
#      |analyst|london|
#      |senior |london|
#      |Analyst|bristol|

#   Scenario Outline: I enter either keyword or location
#     When I enter "<keyword>" in job title or "<locationkeyword>" in location and click search
#     Then I should see only the results matching "<keyword>" or "<locationkeyword>" in a new page
#     And include the number of jobs found, partial job description, no of vaccancies, location, salary, job grade, closing date
#     When I select a job that matches my criteria
#     Then I should be shown a full description of the job in a new page with salary min and max, closing date
#     Examples:
#       |keyword|locationkeyword|
#       |business analyst||
#       ||cardiff|

 #  Scenario:
 #      When I don't enter "<keyword>" in job title or "<locationkeyword>" in location and click search
 #      Then I should be informed that either keyword or location is mandatory

