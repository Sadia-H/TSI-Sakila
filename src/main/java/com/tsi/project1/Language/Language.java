package com.tsi.project1.Language;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "last_update")
    @Setter
    private LocalDateTime lastUpdate = LocalDateTime.now();
//
    public Language() {

    }

//    @JsonCreator
//    public Language(@JsonProperty("languageId") Short languageId,
//                    @JsonProperty("name") String name,
//                    @JsonProperty("lastUpdate") LocalDateTime lastUpdate) {
//        this.languageId = languageId;
//        this.name = name;
//        this.lastUpdate = lastUpdate;
//    }


}
