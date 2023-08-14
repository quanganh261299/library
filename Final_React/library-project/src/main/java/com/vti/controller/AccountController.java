package com.vti.controller;

import com.vti.dto.AccountDto;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private IAccountService service;

    private ModelMapper mapper;

    @Autowired
    public AccountController(IAccountService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<AccountDto> findAll(AccountFilterForm form, Pageable pageable) {
        Page<Account> accounts = service.findAll(form, pageable);
        return accounts.map(account -> mapper.map(account, AccountDto.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable("id") Integer id){
        Account account = service.findById(id);
        return mapper.map(account, AccountDto.class).withSelfRel();
    }

    @PostMapping
    public void create(@RequestBody AccountCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody AccountUpdateForm form){
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
