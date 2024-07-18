package com.tsi.project1;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "language")
@Getter
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Short languageId;

    @Column(name = "name")
    @Setter
    private String name;

    @Column(name = "last_update")
    @Setter
    private LocalDateTime lastUpdate = LocalDateTime.now();

    public Language() {

    }


}
