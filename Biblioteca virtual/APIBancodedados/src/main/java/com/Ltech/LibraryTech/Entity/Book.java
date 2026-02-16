package com.Ltech.LibraryTech.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_author")
    private Integer idAuthor;
    @Column(name = "summary")
    private String summary;
    @Column(name = "id_genre")
    private Integer idGenre;
    @ManyToOne
    @JoinColumn(name = "id_author", insertable = false, updatable = false)
    private Author author;
    @ManyToOne
    @JoinColumn(name = "id_genre", insertable = false, updatable = false)
    private Genre genre;
    @Column(name = "price",precision = 10,scale = 2)
    private BigDecimal price;
}
