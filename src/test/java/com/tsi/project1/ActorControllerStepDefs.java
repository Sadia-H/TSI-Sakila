package com.tsi.project1;

import com.tsi.project1.Actor.Actor;
import com.tsi.project1.Actor.ActorController;
import com.tsi.project1.Actor.ActorInput;
import com.tsi.project1.Actor.ActorService;
import com.tsi.project1.Film.Film;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ActorControllerStepDefs {

    ActorService mockService;
    ActorController controller;
    Actor actualOutput;
    Exception caughtException;
    ActorInput actorInput;

    @Before
    public void setup() {
        mockService = mock(ActorService.class);
        controller = new ActorController(mockService);
    }

    @Given("an actor exists with ID {short}")
    public void anActorExistsWithID(short id) {
        List<Film> films = List.of(new Film("FilmExample1"), new Film("FilmExample2"));
       final var actor = new Actor(id, "Jo", "Smith", List.of());
         doReturn(actor).when(mockService).findActor(id);
    }

    @Given("a valid ActorInput request body")
    public void aValidActorInputRequestBody() {
        actorInput = new ActorInput("Jane", "Doe");
        Actor createdActor = new Actor((short) 42, "Jane", "Doe", List.of());
        doReturn(createdActor).when(mockService).createActor(actorInput);
    }

    @When("a GET request is made for an actor with ID {short}")
    public void aGETRequestIsMadeForAnActorWithId(short id) {
        try {
            actualOutput = controller.findActor(id);

        } catch (Exception ex) {
            caughtException = ex;
        }
    }

    @When("a POST request is made to the actors collection")
    public void aPOSTRequestIsMadeToTheActorsCollection() {
        try {
            actualOutput = controller.createActor(actorInput);

        } catch (Exception ex) {
            caughtException = ex;
        }

    }

    @Then("an ActorDetailsOutput is returned")
    public void anActorDetailsOutputIsReturned() {
        assertNotNull(actualOutput);
        assertNull(caughtException);
    }

}
