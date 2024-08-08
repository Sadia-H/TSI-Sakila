Feature: FilmController

  Scenario: An film is read by ID
    Given a film exists with ID 42
    When a GET request is made for a film with ID 42
    Then an film response is returned