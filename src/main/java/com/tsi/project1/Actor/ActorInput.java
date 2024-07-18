package com.tsi.project1.Actor;

import com.tsi.project1.ValidationGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

//
@Data
public class ActorInput {

    @NotNull(groups = {ValidationGroup.Create.class})
    @Size(min=1, max=45)
    private String firstName;

    @NotNull(groups = ValidationGroup.Create.class)
    @Size(min=1, max=45)
    private String lastName;


}
