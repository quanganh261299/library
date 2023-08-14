package com.vti.repository;

import com.vti.entity.BorrowingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBorrowingHistoryRepository extends JpaRepository<BorrowingHistory, Integer>, JpaSpecificationExecutor<BorrowingHistory> {
}
