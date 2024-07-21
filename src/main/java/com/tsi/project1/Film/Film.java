package com.tsi.project1.Film;

import com.tsi.project1.Actor.Actor;
import com.tsi.project1.Language.Language;
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

//    @Column(name = "language_id")
//    @Setter
//    private Short languageId;

    @ManyToOne
    @JoinColumn(name = "language_id")
    @Setter
    private Language language;

    @Column(name = "original_language_id")
    @Setter
    private Short originalLanguageId;

//    @ManyToOne
//    @JoinColumn(name = "language_id", nullable = false)
//    @Setter
//    private Language language;


//    @ManyToOne
//    @JoinColumn(name = "original_language_id")
//    @Setter
//    private Language originalLanguageId;

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

    @ManyToMany(mappedBy = "films")
    private List<Actor> cast = new ArrayList<>();



//    public void setLanguage(Language language) {
//    }
}
