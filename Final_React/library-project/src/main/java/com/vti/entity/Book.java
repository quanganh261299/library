package com.vti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "title", length = 50, unique = true, nullable = false)
    private String title;

    @Lob
    @Column(name = "cover_image")
    private byte[] coverImage;

    @Column(name = "author", length = 50, nullable = false)
    private String author;

    @Column(name = "category", length = 50, nullable = false)
    private String category;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "status", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean status;

    @OneToOne(mappedBy = "book")
    private BorrowingHistory borrowingHistoryList;
}
