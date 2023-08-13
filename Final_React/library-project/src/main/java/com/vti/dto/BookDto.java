package com.vti.dto;

import com.vti.controller.BookController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Getter
@Setter
public class BookDto extends RepresentationModel<BookDto> {
    private Integer id;
    private String title;
    private String author;
    private byte[] coverImage;
    private String category;
    private String status;

    public BookDto withSelfRel(){
        Link link = linkTo(methodOn(BookController.class).findById(id)).withSelfRel();
        return add(link);
    }
}
