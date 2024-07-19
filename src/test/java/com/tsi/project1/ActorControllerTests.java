package com.tsi.project1;

import com.tsi.project1.Actor.Actor;
import com.tsi.project1.Actor.ActorController;
import com.tsi.project1.Actor.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ActorControllerTests {


    private ActorController actorController;


    @BeforeEach
    public void setup () {
        final var mockService = mock(ActorService.class);

        final var actor = new Actor((short)1, "PENELOPE", "GUINESS");

        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(mockService).findActor(any());
        doReturn(actor).when(mockService).findActor((short)1);
        //doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(mockService).findActor(argThat(id -> id != 1));
        // doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(mockService).findActor(any());

//        when(mockService.findActor((short)1)).thenReturn(new Actor());
//        when(mockService.findActor((short)1)).thenReturn(actor);
        //doNothing().when(mockService).deleteActor();

        actorController = new ActorController(mockService);
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
//        Assertions.assertThrows(ResponseStatusException.class, () - > {
//           actorController.findActor((short)2)
//        })

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




//    @Test
//    public void exampleTest() {
//        final var expected = 4;
//        final var actual = 2+2;
//        Assertions.assertEquals(expected, actual, "2 plus 2 should equal 4");
//    }




}
