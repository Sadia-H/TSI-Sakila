package com.tsi.project1;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/{filmId}")
    public Film getFilm(@PathVariable Short filmId) {
        return filmRepository.findById(filmId).get();
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        film.setLastUpdate(LocalDateTime.now());
        return filmRepository.save(film);
    }

    @PutMapping("/{filmId}")
    public Film updateFilm(@PathVariable Short filmId, @RequestBody Film filmData)  {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Film not found."));
        film.setTitle(filmData.getTitle());
        film.setDescription(filmData.getDescription());
        film.setReleaseYear(filmData.getReleaseYear());
        film.setLanguageId(filmData.getLanguageId());
        film.setOriginalLanguageId(filmData.getOriginalLanguageId());
        film.setRentalRate(filmData.getRentalRate());
        film.setLength(filmData.getLength());
        film.setReplacementCost(filmData.getReplacementCost());
        film.setRating(filmData.getRating());
        film.setSpecialFeatures(filmData.getSpecialFeatures());
        film.setLastUpdate(LocalDateTime.now());

        return filmRepository.save(film);
    }


//    @PutMapping("/{id}")
//    public Film updateFilm(@PathVariable Short filmId, @RequestBody Film film) {
//        return filmRepository.save(film);
//    }

    @DeleteMapping("/{filmId}")
    public void deleteFilm(@PathVariable Short filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Actor not found."));
        filmRepository.delete(film);



    }



}
