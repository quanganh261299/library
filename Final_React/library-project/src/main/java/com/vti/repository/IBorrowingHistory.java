package com.vti.repository;

import com.vti.entity.BorrowingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBorrowingHistory extends JpaRepository<BorrowingHistory, Integer> {
}
