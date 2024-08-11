package com.tsi.project1.ServiceTests;


import com.tsi.project1.actor.Actor;
import com.tsi.project1.actor.ActorInput;
import com.tsi.project1.actor.ActorRepository;
import com.tsi.project1.actor.ActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ActorServiceTests {

    @Autowired
    private ActorService actorService;

    @MockBean
    private ActorRepository mockRepository;

    private Actor actor;

    @BeforeEach
    void setup () {
        actor = new Actor();
        actor.setId((short)1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
    }

    @Test
    void actorServiceTestFindAllActors () {
        when(mockRepository.findAll()).thenReturn(List.of(actor));
        List<Actor> result = actorService.findAllActors();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(actor, result.get(0));
        verify(mockRepository, times(1)).findAll();

    }

    @Test
    void actorServiceTestFindActor() {
        when(mockRepository.findById((short)1)).thenReturn(Optional.of(actor));
        Actor result = actorService.findActor((short)1);

        assertNotNull(result);
        assertEquals(actor, result);
        verify(mockRepository, times(1)).findById((short)1);
    }

    @Test
    void actorServiceTestCreateActor() {
        ActorInput actorInput = new ActorInput("Jane", "Doe");
        Actor newActor = new Actor();
        newActor.setFirstName("Jane");
        newActor.setLastName("Doe");

        when(mockRepository.save(any(Actor.class))).thenReturn(newActor);

        Actor createdActor = actorService.createActor(actorInput);
        assertNotNull(createdActor);
        assertEquals("Jane", createdActor.getFirstName());
        assertEquals("Doe", createdActor.getLastName());
        verify(mockRepository, times(1)).save(any(Actor.class));

    }

    @Test
    void actorServiceTestUpdateActor() {
        ActorInput actorInput = new ActorInput("Jane", "Doe");
        when(mockRepository.findById((short)1)).thenReturn(Optional.of(actor));
        when(mockRepository.save(any(Actor.class))).thenReturn(actor);

        Actor result = actorService.updateActor((short)1, actorInput);
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(mockRepository, times(1)).findById((short)1);
        verify(mockRepository, times(1)).save(actor);
    }

    @Test
    void actorServiceTestUpdateActorNotFound () {
        ActorInput updateInput = new ActorInput("Jane", "Doe");
        when(mockRepository.findById((short)1)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            actorService.updateActor((short)1, updateInput);
        });

        assertEquals("Actor not found.", e.getMessage());
        verify(mockRepository, times(1)).findById((short)1);
    }

    @Test
    void actorServiceTestPatchActor () {
        ActorInput actorInput = new ActorInput("Jane", null);
        when(mockRepository.findById((short)1)).thenReturn(Optional.of(actor));
        when(mockRepository.save(any(Actor.class))).thenReturn(actor);

        Actor result = actorService.patchActor((short) 1, actorInput);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(mockRepository, times(1)).findById((short) 1);
        verify(mockRepository, times(1)).save(actor);


    }

    @Test
    void actorServiceTestPatchActorNotFound() {
        ActorInput patchInput = new ActorInput("Jane", null);

        when(mockRepository.findById((short) 1)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            actorService.patchActor((short) 1, patchInput);
        });

        assertEquals("Actor not found.", e.getMessage());
        verify(mockRepository, times(1)).findById((short) 1);
    }

    @Test
    void actorServiceTestDeleteActor () {
        when(mockRepository.findById((short)1)).thenReturn(Optional.of(actor));
        actorService.deleteActor((short)1);

        verify(mockRepository, times(1)).findById((short)1);
        verify(mockRepository, times(1)).delete(actor);
    }

    @Test
    void testDeleteActorNotFound() {
        when(mockRepository.findById((short) 1)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            actorService.deleteActor((short) 1);
        });

        assertEquals("Actor not found.", e.getMessage());
        verify(mockRepository, times(1)).findById((short) 1);
    }


}
