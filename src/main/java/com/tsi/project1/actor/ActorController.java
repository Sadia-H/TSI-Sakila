package com.tsi.project1.actor;

import com.tsi.project1.ValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/actors")
public class ActorController {


    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }


    @GetMapping
    public List<Actor> findAllActors() {
        return actorService.findAllActors();
    }

    @GetMapping("/{id}")
    public Actor findActor(@PathVariable Short id) {
        Actor actor = actorService.findActor(id);
        if (actor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor with ID " + id + " not found");
        }
        return actor;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(
           @Validated(ValidationGroup.Create.class) @RequestBody ActorInput data) {
             if (data.getFirstName() == null || data.getFirstName().isEmpty() ||
                    data.getLastName() == null || data.getLastName().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid actor data");
            }
        return actorService.createActor(data);
    }

    @PutMapping("/{id}")
    public Actor updateActor(
            @Validated(ValidationGroup.Update.class) @PathVariable Short id, @RequestBody ActorInput actorInput) {
        return actorService.updateActor(id, actorInput);
    }


    @PatchMapping("/{id}")
    public Actor patchActor(@PathVariable Short id, @RequestBody ActorInput actorInput) {
        return actorService.patchActor(id, actorInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActor(@PathVariable Short id) {
        Actor actor = actorService.findActor(id);
        if (actor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");
        }
        actorService.deleteActor(id);

    }


}
