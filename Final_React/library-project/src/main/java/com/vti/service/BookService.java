package com.vti.service;

import com.vti.entity.Book;
import com.vti.form.AccountCreateForm;
import com.vti.form.BookCreateForm;
import com.vti.form.BookFilterForm;
import com.vti.form.BookUpdateForm;
import com.vti.repository.IBookRepository;
import com.vti.specification.BookSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    private IBookRepository repository;
    private ModelMapper mapper;

    public BookService(IBookRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Book findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Book> findAll(BookFilterForm form, Pageable pageable){
        Specification<Book> spec = BookSpecification.buildSpec(form);
        return repository.findAll(spec, pageable);
    }

    @Override
    public void create(BookCreateForm form){
        Book book = mapper.map(form, Book.class);
        if (book.getStatus() == null) {
            book.setStatus(Book.Status.AVAILABLE);
        }
        repository.save(book);
    }

    @Override
    public void update(BookUpdateForm form){
        Book book = mapper.map(form, Book.class);
        repository.save(book);
    }

    @Override
    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Integer> ids){
        repository.deleteAllById(ids);
    }
}
