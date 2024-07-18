package com.tsi.project1.ActorResponse;

import com.tsi.project1.Actor.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/actor-responses")
public class ActorResponseController {

    @Autowired
    private ActorRepository actorRepository;

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
