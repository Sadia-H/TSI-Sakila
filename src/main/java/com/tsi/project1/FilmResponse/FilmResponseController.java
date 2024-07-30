package com.tsi.project1.FilmResponse;


import com.tsi.project1.Film.Film;
import com.tsi.project1.Film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping ("/api/partialFilms")
public class FilmResponseController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<FilmResponse> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(FilmResponse:: new)
                .toList();

    }


    @GetMapping("/{filmId}")
    public FilmResponse getFilmById(@PathVariable Short filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Film not found."));
        return new FilmResponse(film);
    }

}
