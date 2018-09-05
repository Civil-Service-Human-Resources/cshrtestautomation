Scenario 1 - Invalid email:

Given that I am creating an account

and I've entered a invalid email address even after we remove white space

When I select to "Create account"

Then a GOV.UK error message appears stating "There has been a problem" + "you have entered an invalid email"

And this message links to the "Email" field which displays the same error

Scenario 2 - Invalid password:

Given that I am creating an account

And I have entered an invalid password // i.e. < a) 8 characters; b) at least 1 number or symbol; and c) upper and lower case letters (i.e. same as LPG)

When I select to "Create account"

Then a GOV.UK error message appears stating "There has been a problem" + "Your password must have 8 or more characters"

Scenario 3 - Input missing from a mandatory field:

Given that I am creating an account

And I not included a value in the <field> field //all fields are mandatory

When I select to "Create account"

Then a GOV.UK error message appears stating "There has been a problem" + "You need to enter a <field label>"

And this links to the field which displays the same error

Scenario 4 - Confirm password doesn't match initial password:

Given that I am creating an account

And my repeated password doesn't match my original password

When I select to "Create account"

Then a GOV.UK error message appears stating "There has been a problem" + "Your confirmation password doesn't match"

And the message links to the "Confirm password" field