package com.vti.service;

import com.vti.entity.Book;
import com.vti.form.BookCreateForm;
import com.vti.form.BookFilterForm;
import com.vti.form.BookUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
    Book findById(Integer id);

    Page<Book> findAll(BookFilterForm form, Pageable pageable);

    void create(BookCreateForm form);

    void update(BookUpdateForm form);

    void deleteById(Integer id);

    void deleteAllById(List<Integer> ids);
}
