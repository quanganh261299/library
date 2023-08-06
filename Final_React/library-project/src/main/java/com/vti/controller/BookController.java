package com.vti.controller;

import com.vti.entity.Account;
import com.vti.entity.Book;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountUpdateForm;
import com.vti.form.BookCreateForm;
import com.vti.form.BookUpdateForm;
import com.vti.service.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private IBookService service;

    private ModelMapper mapper;

    public BookController(IBookService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<Book> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable("id") Integer id){
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody BookCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody BookUpdateForm form){
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllById(@RequestBody List<Integer> ids){
        service.deleteAllById(ids);
    }
}
