Feature:In order to filter jobs based on departments

  Background:
    Given I open cshr website
    Then I should see homepage with options to search for location and keyword

  Scenario Outline:Search for jobs in a specific location either with or without keyword and Only results for the departments that I select are displayed
    When I enter <keyword> in job title and <locationkeyword> in location
    And I click on search
    When I enter <newKeyword> in the keyword
    And I enter new radius <radius>
    And I expand departments accordion
    When I enter <departments> in the departmentbox
    And I click update results
    #Then I should see only the results matching "<newKeyword>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>" or "<regions>" or "<overseas>" locations and departmentid "<departments>" in a new page
    Examples:
    |keyword|locationkeyword|departments                        |newKeyword|latitude |longitude           |radius|regions|overseas|
    |program    |SA1           |Driver and Vehicle Licensing Agency    |          |51.518043|-0.109374         | 30    |      |  true  |
    |customer    |NN1 2TA    |Ministry of Housing, Communities & Local Government||51.449572| -2.592711| 50   ||true|

  Scenario Outline: Overseas jobs are not displayed when I untick overseas option
      When I enter "<keyword>" in job title and "<locationkeyword>" in location
      And I click on search
      When I select overseas "<overseas>" flag
      And I enter new radius "<radius>"
      And I click on search
      Then I should see only the results matching "<keyword>" in "<locationkeyword>" and "<radius>" with "<latitude>" and "<longitude>" or "<regions>" or "<overseas>" locations in a new page
    Examples:
        |keyword|locationkeyword  |latitude |longitude    |radius|regions|overseas|
        |we    |SW1Y             |51.518043|-0.109374    | 30    |Greater London      |  false  |

  Scenario Outline: Department logos are displayed
      When I enter "<keyword>" in job title and "<locationkeyword>" in location
      And I click on search
      Then I verify either a logo or department name is displayed in the job description
  Examples:
    |keyword|locationkeyword|
    |  animal  |    bath  |

  #This scenario is not yet matured currently only displaying jobs with public opening date less than today and internal opening date not in future
  #Better have all different types of date jobs in specific post codes and search for less than 5 miles and check
  Scenario Outline: To check if the jobs that have public, government, internal opening dates display appropriately in the frontend
   When I enter "<keyword>" in job title and "<locationkeyword>" in location
   And I click on search
   #the sentence seems big any other way to write it?
   And The job is with "<keyword>" in "<locationkeyword>" and "<radius>" and "<latitude>" and "<longitude>" or "<regions>" or "<overseas>" and public opening date is "<publicopeningdate>" and government opening date is "<govopeningdate>" and internal opening date is "<internalopeningdate>" The job displayed is "<displayed>"
    Examples:
    |keyword|locationkeyword|latitude |longitude|radius|publicopeningdate|govopeningdate |internalopeningdate|displayed|regions|overseas|
    |web    |SW1Y           |51.518043|-0.109374| 30   |     past        |     past      |        past       | true    |greater london       |  true  |
 #   |web    |SW1Y           |51.518043|-0.109374| 30   |     future      |               |                   | false   |||
 #   |web    |SW1Y           |51.518043|-0.109374| 30   |     future      | future        |    future         | false   |||
 #   |web    |SW1Y           |51.518043|-0.109374| 30   |     future      | past          |    future         | false   |||
 #   |web    |SW1Y           |51.518043|-0.109374| 30   |     future      | past          |    past           | false   |||

#  Scenario:
#    Given A job exists with a public opening date in the past and not greater than internal opening date and government opening date in the future and internal opening date in past
#    Then I should not see the job

#  Scenario:
#    Given A job exists with a public opening date in the past and greater than internal opening date and government opening date in the future and internal opening date in past
#    Then I should see the job




