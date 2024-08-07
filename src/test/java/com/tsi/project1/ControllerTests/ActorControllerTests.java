package com.tsi.project1.ControllerTests;

import com.tsi.project1.actor.Actor;
import com.tsi.project1.actor.ActorController;
import com.tsi.project1.actor.ActorInput;
import com.tsi.project1.actor.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class ActorControllerTests {

    private ActorController actorController;
    private ActorService mockService;


    @BeforeEach
    public void setup () {
        mockService = mock(ActorService.class);
        actorController = new ActorController(mockService);

        final var actor = new Actor((short)1, "PENELOPE", "GUINESS", new ArrayList<>());
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(mockService).findActor(any());
        doReturn(actor).when(mockService).findActor((short)1);
    }

    @Test
    void actorControllerFindAllActors() {

        //mock data to be returned by mock service
        final var actor1 = new Actor((short)2, "JOHN", "DOE", new ArrayList<>());
        final var actor2 = new Actor((short)3, "SARAH", "DOE", new ArrayList<>());

        //set up mock service - returns list of actors
        List<Actor> actors = Arrays.asList(actor1, actor2);
        when(mockService.findAllActors()).thenReturn(actors);

        //call the method on the controller
        List<Actor> result = actorController.findAllActors();

        //verify results
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The result should contain 2 actors.");
        assertEquals(actor1, result.get(0), "The first actor should be JOHN DOE");
        assertEquals(actor2, result.get(1), "The second actor should be SARAH DOE");

    }

    @Test
    void actorControllerFindActorReturnsAnExistingActor() {
        final var expectedId = (short)1;
        final var expectedFirstName = "PENELOPE";
        final var expectedLastName = "GUINESS";
        final var actual = actorController.findActor((short) 1);
        assertEquals(expectedId, actual.getId());
        assertEquals(expectedFirstName, actual.getFirstName());
        assertEquals(expectedLastName, actual.getLastName());

    }

    @Test
    void actorControllerFindActorThrows404WhenInvalidId() {

        Exception exception = null;
        try {
            actorController.findActor((short)2);
        } catch (Exception e) {
            exception = e;
        }

        assertNotNull(exception);
        Assertions.assertInstanceOf(ResponseStatusException.class, exception);
        assertEquals(HttpStatus.NOT_FOUND, ((ResponseStatusException)exception).getStatusCode());

    }


    @Test
    void actorControllerCreateActorWithValidDetails(){
        final var newActor = new Actor((short)2, "JOHN", "DOE", new ArrayList<>());
        final var actorInput = new ActorInput("JOHN", "DOE");

        doReturn(newActor).when(mockService).createActor(any(ActorInput.class));

        Actor createdActor = actorController.createActor(actorInput);

        assertNotNull(createdActor);
        assertEquals(newActor.getId(), createdActor.getId());
        assertEquals(newActor.getFirstName(), createdActor.getFirstName());
        assertEquals(newActor.getLastName(), createdActor.getLastName());

        verify(mockService, times(1)).createActor(any(ActorInput.class));
    }

    @Test
    void actorControllerPatchActorWithValidDetails() {

        //mock data
        Short actorId = (short) 5;
        ActorInput actorInput = new ActorInput("EDDY", "CHASE");
        Actor updatedActor = new Actor(actorId, "EDDY", "CHASE", new ArrayList<>());

        //mock behaviour
        when(mockService.patchActor(anyShort(), any(ActorInput.class))).thenReturn(updatedActor);

        //call controller method
        Actor result = actorController.patchActor(actorId, actorInput);

        //verify results
        assertNotNull(result, "The result should not be null");
        assertEquals(actorId, result.getId(), "The actor ID should be 5.");
        assertEquals("EDDY", result.getFirstName(), "The actor's first name should be EDDY.");
        assertEquals("CHASE", result.getLastName(), "The actor's last name should be CHASE");

    }






}
