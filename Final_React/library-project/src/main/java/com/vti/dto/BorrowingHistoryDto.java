package com.vti.dto;

import com.vti.controller.BookController;
import com.vti.controller.BorrowingHistoryController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class BorrowingHistoryDto extends RepresentationModel<BorrowingHistoryDto> {
    private Integer id;
    private String accountFullName;
    private String bookTitle;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public BorrowingHistoryDto withSelfRel(){
        Link link = linkTo(methodOn(BorrowingHistoryController.class).findById(id)).withSelfRel();
        return add(link);
    }
}
