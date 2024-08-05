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
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ActorControllerStepDefs {

    ActorService mockService;
    ActorController controller;
    Actor actualOutput;
    ActorInput actorInput;
    Exception caughtException;

    String errorMessage;


    @Before
    public void setup() {
        mockService = mock(ActorService.class); //creates mock service class
        controller = new ActorController(mockService); //controller class with the mock service
    }

    @Given("an actor exists with ID {short}")
    public void anActorExistsWithID(short id) {
        List<Film> films = List.of(new Film("FilmExample1"), new Film("FilmExample2"));
       final var actor = new Actor(id, "Jo", "Smith", List.of()); //dummy actor
         doReturn(actor).when(mockService).findActor(id);
    }

    @Given("no actors exist with ID {short}")
    public void noActorsExistWithID(short id) {
        doReturn(null).when(mockService).findActor((short) 13);
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

    @Then("a ResponseStatusException is thrown")
    public void aResponseStatusExceptionIsThrown() {
        assertNull(actualOutput);
        assertNotNull(caughtException);
        assertNull(errorMessage);
    }
}
