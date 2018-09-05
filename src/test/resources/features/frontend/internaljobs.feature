
 # Given I login and authenticate as a user from department HMRC
 #   And I search for jobs
#    Then I should see jobs from HMRC
 #   And I should see accross government jobs from other departments
 #   And I should see public jobs
#

#  Given I login and authenticate as a user from department HMRC
 # And I search for jobs
 # Then I should not see internal only jobs from other departments DVLA
 # And I should see accross government jobs from other departments
 # And I should see public jobs

 # Dept Shared jobs with DEFRA and Met Office

  Given I authenticate as Gov Dept <MOD>
  When I search
  Then I should see Mod Jobs
  And I should see Defra jobs
  And I should see Met Office jobs
  And I should see across Gov Jobs
  And I should see public jobs

  Given I authenticate as Gov Dept <Defra>
  When I search
  Then I should see Mod Jobs
  And I should see Defra jobs
  And I should see Met Office jobs
  And I should see across Gov Jobs
  And I should see public jobs


  Given I am not authenticated as a user
  When I search
  Then I should see public jobs
  And I shouldn’t see across Gov jobs
  And I shouldn’t see internal jobs

 #Department not sharing jobs
 Given I authenticate as Gov Dept <British Council>
 When I search
 Then I should see British Council Jobs
 And I should see across Gov Jobs
 And I should see public jobs

 # Internal Jobs-
 # Internal Opening Date – Present or past date
 # Across Gov date – Future date
 # Public opening date – Future date

 # Across Gov Jobs-
 # Internal Opening Date –past date
 # Across Gov date – Present or past date
 # Public opening date – Future date

 # Public jobs-
 # Internal Opening Date –past date
 # Across Gov date –past date
 # Public opening date – present date or past date


