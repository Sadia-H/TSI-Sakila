package com.tsi.project1.Film;

import com.tsi.project1.ValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/{filmId}")
    public Film getFilmById(@PathVariable Short filmId) {
        return filmService.getFilmById(filmId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film createFilm(
            @Validated(ValidationGroup.Create.class) @RequestBody FilmInput filmInput) {
            return filmService.createFilm(filmInput);
    }

    @PutMapping("/{filmId}")
    public Film updateFilm(
            @Validated (ValidationGroup.Update.class) @PathVariable Short filmId, @RequestBody FilmInput filmInput) {
            return filmService.updateFilm(filmId, filmInput);
    }



    @DeleteMapping("/{filmId}")
    public void deleteFilm(@PathVariable Short filmId) {

      filmService.deleteFilm(filmId);

    }









}
