package com.tsi.project1.ActorResponse;

import com.tsi.project1.actor.Actor;
import lombok.Getter;

@Getter
public class PartialActorResponse {
    private final Short id;
    private final String firstName;
    private final String lastName;

    public PartialActorResponse(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
    }
}