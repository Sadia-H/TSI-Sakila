package com.tsi.project1.Film;

import com.tsi.project1.actor.Actor;
import com.tsi.project1.actor.ActorRepository;
import com.tsi.project1.Language.Language;
import com.tsi.project1.Language.LanguageRepository;
import com.tsi.project1.ValidationGroup;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/{filmId}")
    public Film getFilmById(@PathVariable Short filmId) {

        return filmRepository.findById(filmId).get();
    }

    @PostMapping
    public Film createFilm(@Validated(ValidationGroup.Create.class) @RequestBody FilmInput filmInput) {
        Language language = languageRepository.findById(filmInput.getLanguageId())
                .orElseThrow(() -> new IllegalArgumentException("Language not found."));

        List<Actor> cast = filmInput.getActorIds().stream()
                .map(id -> actorRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Actor not found: " + id)))
                        .toList();


        Film film = new Film();
        film.setTitle(filmInput.getTitle());
        film.setDescription(filmInput.getDescription());
        film.setReleaseYear(filmInput.getReleaseYear());
        film.setLanguage(language);
        film.setOriginalLanguageId(filmInput.getOriginalLanguageId());
        film.setRentalRate(filmInput.getRentalRate());
        film.setLength(filmInput.getLength());
        film.setReplacementCost(filmInput.getReplacementCost());
        film.setRating(filmInput.getRating());
        film.setSpecialFeatures(filmInput.getSpecialFeatures());
        film.setLastUpdate(LocalDateTime.now());
        film.setCast(cast);

        return filmRepository.save(film);
    }

    @PutMapping("/{filmId}")
    public Film updateFilm(@Validated (ValidationGroup.Update.class) @PathVariable Short filmId, @RequestBody FilmInput filmInput) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Film not found."));
        BeanUtils.copyProperties(filmInput, film, "filmId", "lastUpdate");
        film.setLastUpdate(LocalDateTime.now());
        return filmRepository.save(film);
    }

    @DeleteMapping("/{filmId}")
    public void deleteFilm(@PathVariable Short filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Actor not found."));
        filmRepository.delete(film);



    }



}
