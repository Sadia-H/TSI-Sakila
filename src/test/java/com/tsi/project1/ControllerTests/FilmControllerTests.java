package com.tsi.project1.ControllerTests;


import com.tsi.project1.film.Film;
import com.tsi.project1.film.FilmController;
import com.tsi.project1.film.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class FilmControllerTests {

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


    @Test
    void filmControllerGetAllFilms() {
        // call the method on the controller
        List<Film> result = filmController.getAllFilms();

        // verify results
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The result should contain 2 films.");

        var firstFilm = result.get(0);
        assertEquals((short) 1, firstFilm.getFilmId(), "The first film ID should be 1");
        assertEquals("Parasite", firstFilm.getTitle(), "The first film title should be Parasite");
        assertEquals("description example", firstFilm.getDescription(), "The first film description should be correct");
        assertEquals(Year.of(2024), firstFilm.getReleaseYear(), "The first film release year should be 2024");
        assertEquals(5, firstFilm.getRentalDuration(), "The first film rental duration should be 5.");
        assertEquals(5, firstFilm.getRentalRate(), "The first film rental rate should be 5.");
        assertEquals(100, firstFilm.getLength(), "The first film length should be 100 minutes");
        assertEquals(20, firstFilm.getReplacementCost(), "The first film replacement cost should be 20.");
        assertEquals("PG-13", firstFilm.getRating(), "The first film rating should be PG-13");
        assertEquals("Behind the scenes", firstFilm.getSpecialFeatures(), "The first film special features should be correct");

        var secondFilm = result.get(1);
        assertEquals((short) 2, secondFilm.getFilmId(), "The second film ID should be 2");
        assertEquals("Film Title 2", secondFilm.getTitle(), "The second film title should be Film Title 2");
        assertEquals("Film 2 Description", secondFilm.getDescription(), "The second film description should be correct");
        assertEquals(Year.of(2002), secondFilm.getReleaseYear(), "The second film release year should be 2002");
        assertEquals(3.0, secondFilm.getRentalDuration(), "The second film rental duration should be 3.0");
        assertEquals(10, secondFilm.getRentalRate(), "The second film rental rate should be 10");
        assertEquals(200, secondFilm.getLength(), "The second film length should be 200 minutes");
        assertEquals(50, secondFilm.getReplacementCost(), "The second film replacement cost should be 50");
        assertEquals("R", secondFilm.getRating(), "The second film rating should be R");
        assertEquals("Behind the scenes", secondFilm.getSpecialFeatures(), "The second film special features should be correct.");
    }



}
