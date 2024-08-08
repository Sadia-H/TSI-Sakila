package com.tsi.project1.Language;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "language")
@Getter
public class Language {

    @Id
    @Column(name = "language_id")
    private Short languageId;

    @Column(name = "name")
    @Setter
    private String name;

    public Language() {

    }

    public Language(Short languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }

}
