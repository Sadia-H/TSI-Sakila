package com.tsi.project1.ActorResponse;

import com.tsi.project1.actor.Actor;
import com.tsi.project1.FilmResponse.PartialFilmResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class ActorResponse {
    private final Short id;
    private final String firstName;
    private final String lastName;
    private final List<PartialFilmResponse> films;

    public ActorResponse(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.films = actor.getFilms()
                .stream()
                .map(PartialFilmResponse::new)
                .toList();
    }
}
