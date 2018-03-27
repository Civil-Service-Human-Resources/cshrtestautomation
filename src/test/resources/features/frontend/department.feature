@intest
Feature:In order to filter jobs from departments

  Background:
    Given I open cshr website
    Then I should see homepage with options to search for location and keyword

  Scenario Outline:Search for jobs in a specific location either with or without keyword and Only results for the departments that I select are displayed
    When I enter "<keyword>" in job title and "<locationkeyword>" in location
    And I click on search
    When I enter "<newKeyword>" in the keyword
    And I enter new radius "<radius>"
    And I expand departments accordion
    When I select "<departments>" from the sidebar
    And I click update results
    Then I should see only the results matching "<newKeyword>" and "<departments>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>"
  Examples:
    |keyword|locationkeyword|departments                        |newKeyword|latitude |longitude|radius|
    |web    |SW1Y         |Department for works and pensions  |          |51.518043|-0.109374| 30    |
    |web    |BS1 6NB      |Department for works and pensions,HM Revenue and Customs,Ministry of Defence|soldier|51.449572| -2.592711| 50   |
    |       |london       |Department for works and pensions,HM Revenue and Customs,Ministry of Defence|       |51.518043|-0.109374 | 30   |

  #At the moment not verifying logos
  Scenario Outline: Department logos are displayed
      When I enter "<keyword>" in job title and "<locationkeyword>" in location
      And I click on search
      Then I verify either a logo or department name is displayed in the job description
  Examples:
    |keyword|locationkeyword|
    |  web  |    newcastle  |

  Scenario Outline: To check if the jobs that have public, government, internal opening dates display appropriately in the frontend
   When I enter "<keyword>" in job title and "<locationkeyword>" in location
   And I click on search
   #the sentence seems big any other way to write it?
   And The job is with "<keyword>" in "<locationkeyword>" and "<latitude>" and "<longitude>" and "<radius>" and public opening date is "<publicopeningdate>" and government opening date is "<govopeningdate>" and internal opening date is "<internalopeningdate>" The job displayed is "<displayed>"
    Examples:
    |keyword|locationkeyword|latitude |longitude|radius|publicopeningdate|govopeningdate |internalopeningdate|displayed|
    |web    |SW1Y           |51.518043|-0.109374| 30   |     past        |               |                   | true    |
    |web    |SW1Y           |51.518043|-0.109374| 30   |     future      |               |                   | false   |
    |web    |SW1Y           |51.518043|-0.109374| 30   |     future      | future        |    future         | false   |
    |web    |SW1Y           |51.518043|-0.109374| 30   |     future      | past          |    future         | false   |
    |web    |SW1Y           |51.518043|-0.109374| 30   |     future      | past          |    past           | false   |

#  Scenario:
#    Given A job exists with a public opening date in the past and not greater than internal opening date and government opening date in the future and internal opening date in past
#    Then I should not see the job

#  Scenario:
#    Given A job exists with a public opening date in the past and greater than internal opening date and government opening date in the future and internal opening date in past
#    Then I should see the job




