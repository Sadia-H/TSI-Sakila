package com.tsi.project1.film;

import com.tsi.project1.actor.Actor;
import com.tsi.project1.language.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;

@Entity
@Table(name = "film")
@Getter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    @Setter
    private Short filmId;

    @Column(name = "title")
    @Setter
    private String title;

    @Column (name = "description")
    @Setter
    private String description;

    @Column(name = "release_year")
    @Setter
    private Year releaseYear;


    @ManyToOne
    @JoinColumn(name = "language_id")
    @Setter
    private Language language;

    @Column(name = "original_language_id")
    @Setter
    private Short originalLanguageId;


    @Column(name = "rental_duration")
    @Setter
    private double rentalDuration;

    @Column(name = "rental_rate")
    @Setter
    private double rentalRate;

    @Column(name = "length")
    @Setter
    private int length;

    @Column(name = "replacement_cost")
    @Setter
    private double replacementCost;

    @Column(name = "rating")
    @Setter
    private String rating;

    @Column(name = "special_features")
    @Setter
    private String specialFeatures;

    @Column(name = "last_update")
    @Setter
    private LocalDateTime lastUpdate = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private List<Actor> cast = new ArrayList<>();


    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }


    public Film() {}


    public Film(String title) {
        this.title = title;
    }


}
