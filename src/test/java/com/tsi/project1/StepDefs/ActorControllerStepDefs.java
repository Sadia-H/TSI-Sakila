package com.tsi.project1.StepDefs;

import com.tsi.project1.Actor.Actor;
import com.tsi.project1.Actor.ActorController;
import com.tsi.project1.Actor.ActorInput;
import com.tsi.project1.Actor.ActorService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Given("an invalid ActorInput request body")
    public void anInvalidActorInputRequestBody() {
        actorInput = new ActorInput(null, "");
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

        } catch (Exception e) {
            caughtException = e;
        }

    }

    @When("a PUT request is made for an actor with ID {short}")
    public void aPUTRequestIsMadeForAnActorWithID(short id) {
        ActorInput updatedActorInput = new ActorInput("UpdatedName", "UpdatedSurname");
        Actor updatedActor = new Actor((short) id, "UpdatedName", "UpdatedSurname", List.of());
        doReturn(updatedActor).when(mockService).updateActor((short) id, updatedActorInput);

        try {
            actualOutput = controller.updateActor((short) id, updatedActorInput);
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @When("a DELETE request is made for an actor with ID {short}")
    public void aDELETERequestIsMadeForAnActorWithID(short id) {
        try {
            controller.deleteActor(id);
        } catch (Exception e) {
            caughtException = e;
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

    @Then("the actor is deleted successfully")
    public void theActorIsDeletedSuccessfully() {
        try {
            controller.deleteActor((short) 10);
            doReturn(null).when(mockService).findActor((short) 10);
            Actor deletedActor = mockService.findActor((short)10);
            assertNull(deletedActor);

        } catch (ResponseStatusException e) {
            assertTrue(e.getStatusCode() == HttpStatus.NOT_FOUND);
        }

    }



}
