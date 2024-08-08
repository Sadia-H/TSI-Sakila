package com.tsi.project1.filmResponse;

import com.tsi.project1.film.Film;
import lombok.Getter;

import java.time.Year;

@Getter
public class PartialFilmResponse {
    private final Short filmId;
    private final String title;
    private final Year releaseYear;

    public PartialFilmResponse (Film film) {
        this.filmId = film.getFilmId();
        this.title = film.getTitle();
        this.releaseYear = film.getReleaseYear();

    }
}