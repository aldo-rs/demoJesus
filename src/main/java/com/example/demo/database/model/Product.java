package com.example.demo.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name="insert_date", nullable = false)
    private LocalDate insertDate;

    @Column(name="number_views")
    private int numberOfViews;

    @Column(name="abbreviation", nullable = false)
    private String abbreviation;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

}
