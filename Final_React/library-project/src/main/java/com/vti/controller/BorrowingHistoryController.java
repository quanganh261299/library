package com.vti.controller;

import com.vti.dto.BookDto;
import com.vti.dto.BorrowingHistoryDto;
import com.vti.entity.Book;
import com.vti.entity.BorrowingHistory;
import com.vti.form.BookFilterForm;
import com.vti.form.BorrowingHistoryFilterForm;
import com.vti.service.IBookService;
import com.vti.service.IBorrowingHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowingHistoryController {
    private IBorrowingHistoryService service;

    private ModelMapper mapper;

    public BorrowingHistoryController(IBorrowingHistoryService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<BorrowingHistoryDto> findAll(BorrowingHistoryFilterForm form, Pageable pageable) {
        Page<BorrowingHistory> borrowList = service.findAll(form, pageable);
        return borrowList.map(borrowingHistory -> mapper.map(borrowingHistory, BorrowingHistoryDto.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public BorrowingHistoryDto findById(@PathVariable("id") Integer id){
        BorrowingHistory borrowingHistory = service.findById(id);
        return mapper.map(borrowingHistory, BorrowingHistoryDto.class).withSelfRel();
    }

}
