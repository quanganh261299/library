package com.vti.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 100, unique = true, nullable = false)
    private String title;

    @Column(name = "author", length = 50, nullable = false)
    private String author;

    @Column(name = "publisher", length = 50, nullable = false)
    private String publisher;

    @Column(name = "publication_date", length = 50, nullable = false)
    private LocalDate publicationDate;

    @Lob
    @Column(name = "cover_image")
    private byte[] coverImage;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "status", length = 12, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowingHistory> borrowingHistoryList;

    public enum Status {
        AVAILABLE, UNAVAILABLE
    }
}
