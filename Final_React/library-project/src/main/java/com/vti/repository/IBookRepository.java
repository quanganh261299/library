package com.vti.repository;

import com.vti.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
}
