package com.tsi.project1.Actor;

import com.tsi.project1.ValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }


    @GetMapping
    public List<Actor> findAllActors() {
        return actorService.findAllActors();
    }

    @GetMapping("/{id}")
    public Actor findActor(@PathVariable Short id) {
        return actorService.findActor(id);
               // .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found."));
    }

//    @GetMapping("/{id}")
//    public Actor findActor(@PathVariable Short id) {
//        return actorService.findActor(id);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(
            @Validated(ValidationGroup.Create.class) @RequestBody ActorInput data) {
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
        actorService.deleteActor(id);


    }


}
