package com.tsi.project1.actorResponse;

import com.tsi.project1.actor.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/actor-responses")
public class ActorResponseController {

    @Autowired
    public ActorRepository actorRepository;

    @GetMapping()
    public List<ActorResponse> readAllActors() {
        return actorRepository.findAll()
                .stream()
                .map(ActorResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ActorResponse getActorById(@PathVariable Short id) {
        return actorRepository.findById(id)
                .map(ActorResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


}
