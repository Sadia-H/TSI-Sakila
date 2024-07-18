package com.tsi.project1;

import lombok.Getter;

import java.util.List;

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