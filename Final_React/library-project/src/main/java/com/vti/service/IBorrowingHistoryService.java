package com.vti.service;

import com.vti.entity.BorrowingHistory;
import com.vti.form.BorrowCreateForm;
import com.vti.form.BorrowingHistoryFilterForm;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBorrowingHistoryService {
    BorrowingHistory findById(Integer id);

    Page<BorrowingHistory> findAll(BorrowingHistoryFilterForm form, Pageable pageable);

    @Transactional
    void create(BorrowCreateForm form);
}
