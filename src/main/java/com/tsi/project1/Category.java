//package com.tsi.project1;
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "category")
//@Getter @Setter
//public class Category {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "category_id")
//    private Short id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "last_update")
//    private LocalDateTime lastUpdate;
//
//    @OneToMany(mappedBy = "category")
//    private List<FilmCategory> filmCategories;
//}
