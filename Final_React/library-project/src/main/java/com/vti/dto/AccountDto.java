package com.vti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.controller.AccountController;
import com.vti.entity.Account;
import com.vti.entity.BorrowingHistory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class AccountDto extends RepresentationModel<AccountDto>{
    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private Account.Role role;
    private List<BorrowingHistory> borrowList;

    public AccountDto withSelfRel(){
        Link link = linkTo(methodOn(AccountController.class).findById(id)).withSelfRel();
        return add(link);
    }

    @Getter
    @Setter
    public static class BorrowingHistoryDto {
        @JsonProperty("account_id")
        private Integer id;
        private LocalDateTime borrowDate;
        private LocalDateTime returnDate;
    }
}
