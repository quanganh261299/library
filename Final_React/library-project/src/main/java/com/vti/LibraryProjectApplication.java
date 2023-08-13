package com.vti;

import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper mapper = new ModelMapper();

		mapper.typeMap(AccountCreateForm.class, Account.class)
				.addMappings(mapping -> mapping.skip(Account::setId));
		// khi map từ AccountCreateForm sang Account thì skip phần setId cho Account
		return mapper;
	}

}
