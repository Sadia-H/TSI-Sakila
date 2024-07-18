package com.tsi.project1.Actor;

import com.tsi.project1.ValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping
    public List<Actor> findAllActors () {
        return actorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Actor findActor(@PathVariable Short id) {
        return actorRepository.findById(id).get();
    }

    @PostMapping
    public Actor create(@Validated(ValidationGroup.Create.class) @RequestBody ActorInput data) {
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        return actorRepository.save(actor);
    }

    @PutMapping("/{id}")
    public Actor update( @Validated(ValidationGroup.Update.class) @PathVariable Short id, @RequestBody ActorInput actorInput) {
        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor not found."));

        existingActor.setFirstName(actorInput.getFirstName());
        existingActor.setLastName(actorInput.getLastName());

        return actorRepository.save(existingActor);
    }

    @PatchMapping("/{id}")
    public Actor patchActor(@PathVariable Short id, @RequestBody ActorInput actorInput) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor not found."));

        if(actorInput.getFirstName() != null) {
            actor.setFirstName(actorInput.getFirstName());
        }
        if (actorInput.getLastName() != null ) {
            actor.setLastName(actorInput.getLastName());
        }
        actor.setLastUpdate(LocalDateTime.now());

        return actorRepository.save(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Short id) {
      //  actorRepository.deleteById(id);
        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor not found."));
        actorRepository.delete(existingActor);



    }



}
