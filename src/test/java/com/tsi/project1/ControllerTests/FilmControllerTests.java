package com.tsi.project1.ControllerTests;


import com.tsi.project1.film.Film;
import com.tsi.project1.film.FilmController;
import com.tsi.project1.film.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FilmControllerTests {

    private FilmController filmController;
    private FilmService mockService;

    @BeforeEach
    public void setup() {
        mockService = mock(FilmService.class);
        filmController = new FilmController(mockService);

        //mock data
        Film film1 = new Film();
        film1.setFilmId((short)1);
        film1.setTitle("Parasite");
        film1.setDescription("description example");
        film1.setReleaseYear(Year.of(2024));
        film1.setRentalDuration(5);
        film1.setRentalRate(5);
        film1.setLength(100);
        film1.setReplacementCost(20);
        film1.setRating("PG-13");
        film1.setSpecialFeatures("Behind the scenes");
        film1.setLastUpdate(LocalDateTime.now());

        Film film2 = new Film();
        film2.setFilmId((short) 2);
        film2.setTitle("Film Title 2");
        film2.setDescription("Film 2 Description");
        film2.setReleaseYear(Year.of(2002));
        film2.setRentalDuration(3.0);
        film2.setRentalRate(10);
        film2.setLength(200);
        film2.setReplacementCost(50);
        film2.setRating("R");
        film2.setSpecialFeatures("Behind the scenes");
        film2.setLastUpdate(LocalDateTime.now());

        //set up mock service - returns list of films
        List<Film> films = Arrays.asList(film1, film2);
        when(mockService.getAllFilms()).thenReturn(films);

    }



}
