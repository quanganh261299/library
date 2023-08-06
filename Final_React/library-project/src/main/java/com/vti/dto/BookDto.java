package com.vti.dto;

import com.vti.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookDto {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Date publicationDate;
    private byte[] coverImage;
    private Integer price;
    private String status;
}
