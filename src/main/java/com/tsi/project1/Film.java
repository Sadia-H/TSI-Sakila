package com.tsi.project1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "film")
@Getter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "title")
    @Setter
    private String title;

    @Column (name = "description")
    @Setter
    private String description;

    @Column(name = "release_year")
    @Setter
    private Integer releaseYear;

    @Column(name = "language_id")
    @Setter
    private Short languageId;

    @Column(name = "original_language_id")
    @Setter
    private Short originalLanguageId;

    @Column(name = "rental_duration")
    @Setter
    private Double rentalDuration;

    @Column(name = "rental_rate")
    @Setter
    private Double rentalRate;

    @Column(name = "length")
    @Setter
    private Integer length;

    @Column(name = "replacement_cost")
    @Setter
    private Double replacementCost;

    @Column(name = "rating")
    @Setter
    private String rating;

    @Column(name = "special_features")
    @Setter
    private String specialFeatures;

    @Column(name = "last_update")
    @Setter
    private LocalDateTime lastUpdate = LocalDateTime.now();


}
