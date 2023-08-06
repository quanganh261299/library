package com.vti.dto;

import com.vti.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto{
    private Integer id;
    private String username;
    private String fullName;
    private String role;
}
