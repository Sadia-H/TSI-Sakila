Feature: ActorController

  Scenario: An actor is read by ID
    Given an actor exists with ID 42
    When a GET request is made for an actor with ID 42
    Then an ActorDetailsOutput is returned
#    And the status code is 200

#
#  Scenario: An invalid actor is read by ID
#    Given no actors exist with ID 13
#    When a GET request is made for an actor with id 13
#    Then a ResponseStatusException is thrown
#    And the status code is 404

  Scenario: An actor is created
    Given a valid ActorInput request body
    When a POST request is made to the actors collection
    Then an ActorDetailsOutput is returned
#    And the status code is 204