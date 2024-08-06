Feature: ActorController

  Scenario: An actor is read by ID
    Given an actor exists with ID 42
    When a GET request is made for an actor with ID 42
    Then an ActorDetailsOutput is returned
#    And the status code is 200


  Scenario: An invalid actor is read by ID
    Given no actors exist with ID 13
    When a GET request is made for an actor with ID 13
    Then a ResponseStatusException is thrown
#    And the status code is 404

  Scenario: An actor is created
    Given a valid ActorInput request body
    When a POST request is made to the actors collection
    Then an ActorDetailsOutput is returned
#    And the status code is 204

  Scenario: An invalid request body is sent
    Given an invalid ActorInput request body
    When a POST request is made to the actors collection
    Then a ResponseStatusException is thrown

  Scenario: An actor is updated
    Given an actor exists with ID 15
    And a valid ActorInput request body
    When a PUT request is made for an actor with ID 15
    Then an ActorDetailsOutput is returned

  Scenario: An actor is deleted by ID
    Given an actor exists with ID 10
    When a DELETE request is made for an actor with ID 10
    Then the actor is deleted successfully

  Scenario: An actor is not found for deletion
    Given no actors exist with ID 11
    When a DELETE request is made for an actor with ID 11
    Then a ResponseStatusException is thrown

