package com.tsi.project1.FilmResponse;


import com.tsi.project1.Film.Film;
import com.tsi.project1.Film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/partialFilms")
public class FilmResponseController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/{filmId}")
    public FilmResponse getFilmById(@PathVariable Short filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Film not found."));
        return new FilmResponse(film);
    }

}
