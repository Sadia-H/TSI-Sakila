package com.tsi.project1;

import com.tsi.project1.Actor.Actor;
import com.tsi.project1.Actor.ActorController;
import com.tsi.project1.Actor.ActorInput;
import com.tsi.project1.Actor.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ActorControllerTests {

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
    public void actorControllerFindActorReturnsAnExistingActor() {
        final var expectedId = (short)1;
        final var expectedFirstName = "PENELOPE";
        final var expectedLastName = "GUINESS";
        final var actual = actorController.findActor((short) 1);
        Assertions.assertEquals(expectedId, actual.getId());
        Assertions.assertEquals(expectedFirstName, actual.getFirstName());
        Assertions.assertEquals(expectedLastName, actual.getLastName());

    }

    @Test
    public void actorControllerFindActorThrows404WhenInvalidId() {

        Exception exception = null;
        try {
            actorController.findActor((short)2);
        } catch (Exception e) {
            exception = e;
        }

        Assertions.assertNotNull(exception);
        Assertions.assertInstanceOf(ResponseStatusException.class, exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseStatusException)exception).getStatusCode());

    }


    @Test
    public void actorControllerCreateActorWithValidDetails(){
        final var newActor = new Actor((short)2, "JOHN", "DOE", new ArrayList<>());
        final var actorInput = new ActorInput("JOHN", "DOE");

        doReturn(newActor).when(mockService).createActor(any(ActorInput.class));

        Actor createdActor = actorController.createActor(actorInput);

        Assertions.assertNotNull(createdActor);
        Assertions.assertEquals(newActor.getId(), createdActor.getId());
        Assertions.assertEquals(newActor.getFirstName(), createdActor.getFirstName());
        Assertions.assertEquals(newActor.getLastName(), createdActor.getLastName());

        verify(mockService, times(1)).createActor(any(ActorInput.class));
    }


//    @Test
//    public void actorControllerCreateActorThrowsExceptionForInvalidActor() {
//
//    }




}
