package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountUpdateForm;
import com.vti.repository.IAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    private IAccountRepository repository;

    private ModelMapper mapper;

    @Autowired
    public AccountService(IAccountRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Account findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Account> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Override
    public void create(AccountCreateForm form){
        Account account = mapper.map(form, Account.class);
        repository.save(account);
    }

    @Override
    public void update(AccountUpdateForm form){
        Account account = mapper.map(form, Account.class);
        repository.save(account);
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
