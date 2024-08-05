package com.tsi.project1.Actor;

import com.tsi.project1.Film.Film;
import com.tsi.project1.ValidationGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//
@Data
public class ActorInput {

    @NotNull(groups = {ValidationGroup.Create.class})
    @Size(min=1, max=45)
    private String firstName;

    @NotNull(groups = ValidationGroup.Create.class)
    @Size(min=1, max=45)
    private String lastName;


    public ActorInput(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;

    }
}
