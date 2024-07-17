package com.tsi.project1;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Actor createActor(@RequestBody ActorInput data) {
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        return actorRepository.save(actor);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable Short id, @RequestBody ActorInput actorInput) {
        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor not found."));

        existingActor.setFirstName(actorInput.getFirstName());
        existingActor.setLastName(actorInput.getLastName());

        return actorRepository.save(existingActor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Short id) {
      //  actorRepository.deleteById(id);
        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor not found."));
        actorRepository.delete(existingActor);



    }



}
