Feature: Amazon search

@IntegrationTest

Scenario:  Amazon search and verify
Given Amazon welcome page.
Then Enter seach '<item>'
And select from dropdown
Then Verify search items on next page

 Examples:
	|item|
	|iphone|
	




 

