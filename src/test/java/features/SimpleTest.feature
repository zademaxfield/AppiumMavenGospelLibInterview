@SimpleTest
  Feature: Simple Bookmark Test
    As a user
    I want to add a bookmark to a scripture
    To test the bookmark feature

  Background: User navigates to Jacob chapter 2
    Given I am on Jacob chapter 2
    Then I should see "Jacob"

    @works
    Scenario: Just a test
      When I click the "bookmark" button
      And I click the "plus" button

    @broken
    Scenario: This should fail
      When I click the "bookmark" button
      And I click the "plus" button
      Then I should see "Goat Goat"

#  Scenario: Successfully add and remove bookmark
#    When I click the "bookmark" button
#    And I click the "plus" button
#    Then I should see "Add Bookmark"
#    Then I should see "Jacob 2"
#    Then I should not see "No Bookmarks"
#    And I click the "add" button
#    Then I should see "Jacob"
#    And I click the "bookmark" button
#    Then I should see "Jacob 2"
#    Then I should not see "No Bookmarks"
#    And I click the "bookmark overflow" button
#    And I click the "delete" button
#    And I click the "delete confirm" button
#    Then I should not see "Jacob 2"
#    Then I should see "No Bookmarks"



