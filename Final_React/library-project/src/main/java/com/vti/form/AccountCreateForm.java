package com.vti.form;

import com.vti.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreateForm {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}