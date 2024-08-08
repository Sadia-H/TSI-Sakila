package com.tsi.project1.actor;
import com.tsi.project1.Film.Film;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "actor")
@Getter
@AllArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name")
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Setter
    private String lastName;

    @Column(name = "last_update")
    @Setter
    private LocalDateTime lastUpdate = LocalDateTime.now();

    @ManyToMany(mappedBy = "cast")
    private List<Film> films = new ArrayList<>();

    public Actor () {

    }
    public Actor(Short id, String firstName, String lastName, List<Film> films) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.films = films;
        this.lastUpdate = LocalDateTime.now();
    }

}
