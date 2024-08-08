package com.tsi.project1.StepDefs;

import com.tsi.project1.Film.Film;
import com.tsi.project1.Film.FilmController;
import com.tsi.project1.Film.FilmInput;
import com.tsi.project1.Film.FilmService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class FilmControllerStepDefs {

    FilmService mockService;
    FilmController controller;
    Film actualOutput;
    FilmInput filmInput;
    Exception caughtException;

    @Before
    public void setup() {
        mockService = mock(FilmService.class);
        controller = new FilmController(mockService);
    }

    @Given("a film exists with ID {short}")
    public void aFilmExistsWithID(short filmId) {
        final var film = new Film("filmTitle");
        doReturn(film).when(mockService).getFilmById(filmId);
    }

    @When("a GET request is made for a film with ID {short}")
    public void aGETRequestIsMadeForAFilmWithID(short filmId) {
        try {
            actualOutput = controller.getFilmById(filmId);
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @Then("an film response is returned")
    public void anFilmResponseIsReturned() {
        assertNotNull(actualOutput, "Returned null, expected film to be returned.");
        assertNull(caughtException, "Expected no exceptions but an exception was caught.");
    }
}
