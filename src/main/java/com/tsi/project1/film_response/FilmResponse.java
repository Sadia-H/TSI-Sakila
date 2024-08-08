package com.tsi.project1.film_response;

import com.tsi.project1.actor_response.PartialActorResponse;
import com.tsi.project1.film.Film;
import com.tsi.project1.language.Language;
import lombok.Getter;

import java.time.Year;
import java.util.List;

@Getter
public class FilmResponse {
    private final Short filmId;
    private final String title;
    private final Year releaseYear;
    private final String description;
    private final double rentalDuration;
    private final double rentalRate;
    private final int length;
    private final double replacementCost;
    private final String rating;
    private final String specialFeatures;
    private final Language language;
    private final List<PartialActorResponse> cast;


    public FilmResponse (Film film) {
        this.filmId = film.getFilmId();
        this.title = film.getTitle();
        this.releaseYear = film.getReleaseYear();
        this.description = film.getDescription();
        this.rentalDuration = film.getRentalDuration();
        this.rentalRate = film.getRentalRate();
        this.length = film.getLength();
        this.replacementCost = film.getReplacementCost();
        this.rating = film.getRating();
        this.specialFeatures = film.getSpecialFeatures();
        this.language = film.getLanguage();
        this.cast = film.getCast()
                .stream()
                .map(PartialActorResponse::new)
                .toList();

    }
}
