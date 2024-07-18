package com.tsi.project1.FilmResponse;

import com.tsi.project1.ActorResponse.PartialActorResponse;
import com.tsi.project1.Film.Film;
import lombok.Getter;

import java.time.Year;
import java.util.List;

@Getter
public class FilmResponse {
    private final Short filmId;
    private final String title;
    private final Year releaseYear;
    private final List<PartialActorResponse> cast;

    public FilmResponse (Film film) {
        this.filmId = film.getFilmId();
        this.title = film.getTitle();
        this.releaseYear = film.getReleaseYear();
        this.cast = film.getCast()
                .stream()
                .map(PartialActorResponse::new)
                .toList();

    }
}
