package com.vti.form;

import com.vti.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountFilterForm {
    private String name;
    private Account.Role role;
    private Integer minId;
    private Integer maxId;
}
