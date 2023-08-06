package com.vti.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookUpdateForm {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Date publicationDate;
    private byte[] coverImage;
    private Integer price;
}
