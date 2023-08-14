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
    private String category;
    private String description;
    private byte[] coverImage;
}
