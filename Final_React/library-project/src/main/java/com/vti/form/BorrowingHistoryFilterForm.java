package com.vti.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowingHistoryFilterForm {
    private LocalDate minBorrowDate;
    private LocalDate maxBorrowDate;
    private LocalDate minReturnDate;
    private LocalDate maxReturnDate;
}
