package com.vti.form;

import com.vti.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateForm {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
