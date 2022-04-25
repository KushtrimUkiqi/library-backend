package com.finki.ukim.mk.library.models;

import com.finki.ukim.mk.library.models.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "book_name")
    private String name;

    private String imageUrl;

    private int availableCopies;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    public Book(String name,String imageUrl, Category category, Author author,int availableCopies) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
