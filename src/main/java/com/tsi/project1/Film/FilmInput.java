package com.tsi.project1.Film;

import com.tsi.project1.Language.Language;
import com.tsi.project1.ValidationGroup;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Year;
import java.util.List;

@Data
public class FilmInput {

    @NotNull(groups = {ValidationGroup.Create.class})
    @Size(min = 1, max = 128)
    private String title;

    private String description;
    private Year releaseYear;

    private Short languageId;
    private Language language;

    private Short originalLanguageId;

    @NotNull(groups = {ValidationGroup.Create.class})
    @Min(1) @Max(255)
    private int rentalDuration;

    @NotNull(groups = {ValidationGroup.Create.class})
    @DecimalMin("0.0") @DecimalMax("99.99")
    private double rentalRate;

    private int length;

    @NotNull(groups = {ValidationGroup.Create.class})
    @DecimalMin("0.0") @DecimalMax("999.999")
    private double replacementCost;

    private String rating;
    private String specialFeatures;

    private List<Short> actorIds;

}
