package com.tsi.project1.film_response;


import com.tsi.project1.film.Film;
import com.tsi.project1.film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://13.42.103.58"})
@RequestMapping ("/api/partialFilms")
public class FilmResponseController {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmResponseController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


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
