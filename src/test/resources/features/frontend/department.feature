Feature:In order to filter jobs from departments

  Background:
    Given I open cshr website
    Then I should see homepage with options to search for location and keyword
  Scenario Outline:Search for jobs in a specific location either with or without keyword and Only results for the departments that I select are displayed
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
    When I enter "<newKeyword>" in the keyword
    And I expand departments accordion
    When I select "<departments>" from the sidebar
    And I click update results
    Then I should see only the results matching "<newKeyword>" and "<departments>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>"
  Examples:
    |keyword|locationkeyword|departments                        |newKeyword|latitude |longitude|radius|
    |web    |SW1Y         |Department for works and pensions  |          |51.518043|-0.109374| 30    |
    |web    |BS1 6NB      |Department for works and pensions,HM Revenue and Customs,Ministry of Defence|soldier|51.449572| -2.592711| 30   |
    |       |london       |Department for works and pensions,HM Revenue and Customs,Ministry of Defence|       |51.518043|-0.109374 | 30   |

    #At the moment not verifying logos, not sure whether the same name as department is displayed
  Scenario Outline: Department logos are displayed
      When I enter "<keyword>" in job title and "<locationkeyword>" in location
      And I click on search
      Then I verify either a logo or department name is displayed in the job description
  Examples:
    |keyword|locationkeyword|
    |       |               |

#    Scenario:
#      Given A job exists with a public opening date as future date and government opening date is null and internal opening date in null
#      Then I should not see the job
#  Scenario:
#    Given A job exists with a public opening date as past date and government opening date is null and internal opening date in null
#    Then I should see the job
#    Scenario:
#      Given A job exists with a public opening date in the future and government opening date is future and internal opening date in future
#      Then I should not see the job
#    Scenario:
#    Given A job exists with a public opening date in the future and government opening date is past and internal opening date in future
#      Then I should not see the job
#   Scenario:
#    Given A job exists with a public opening date in the future and government opening date is past and internal opening date in past
#     Then I should not see the job
#  Scenario:
#    Given A job exists with a public opening date in the past and not greater than internal opening date and government opening date in the future and internal opening date in past
#    Then I should not see the job

#  Scenario:
#    Given A job exists with a public opening date in the past and greater than internal opening date and government opening date in the future and internal opening date in past
#    Then I should see the job




