package com.vti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.controller.BookController;
import com.vti.entity.BorrowingHistory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

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
    private BorrowingHistory borrowingHistory;

    public BookDto withSelfRel(){
        Link link = linkTo(methodOn(BookController.class).findById(id)).withSelfRel();
        return add(link);
    }

    @Getter
    @Setter
    public static class BorrowingHistoryDto {
        @JsonProperty("book_id")
        private Integer id;
        private LocalDateTime borrowDate;
        private LocalDateTime returnDate;
    }
}
