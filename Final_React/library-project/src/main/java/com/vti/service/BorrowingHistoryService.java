package com.vti.service;

import com.vti.entity.Account;
import com.vti.entity.Book;
import com.vti.entity.BorrowingHistory;
import com.vti.form.BorrowCreateForm;
import com.vti.form.BorrowingHistoryFilterForm;
import com.vti.repository.IBorrowingHistoryRepository;
import com.vti.specification.BorrowingHistorySpecification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BorrowingHistoryService implements IBorrowingHistoryService {
    private IBorrowingHistoryRepository repository;
    private AccountService accountService;
    private BookService bookService;
    private ModelMapper mapper;

    @Autowired
    public BorrowingHistoryService(IBorrowingHistoryRepository repository, ModelMapper mapper,
                                   AccountService accountService, BookService bookService) {
        this.repository = repository;
        this.mapper = mapper;
        this.accountService = accountService;
        this.bookService = bookService;
    }

    @Override
    public BorrowingHistory findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<BorrowingHistory> findAll(BorrowingHistoryFilterForm form, Pageable pageable){
        Specification<BorrowingHistory> spec = BorrowingHistorySpecification.buildSpec(form);
        return repository.findAll(spec, pageable);
    }

    @Override
    @Transactional
    public void create(BorrowCreateForm form){
        Account account = accountService.findById(form.getAccountId());
        Book book = bookService.findById(form.getBookId());
        if(account!=null && book!=null){
            BorrowingHistory borrowingHistory = new BorrowingHistory();
            borrowingHistory.setAccount(account);
            borrowingHistory.setBook(book);
            repository.save(borrowingHistory);
        }
    }
}
