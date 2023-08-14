package com.vti.specification;

import com.vti.entity.BorrowingHistory;
import com.vti.form.BorrowingHistoryFilterForm;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BorrowingHistorySpecification {
    public static Specification<BorrowingHistory> buildSpec(BorrowingHistoryFilterForm form) {
        return (root, query, builder) -> {
            if (form == null) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();

            if(form.getMinBorrowDate()!=null){
                Path<LocalDateTime> minBorrowDate  = root.get("borrowDate");
                LocalDateTime input = form.getMinBorrowDate().atStartOfDay();
                Predicate predicate = builder.greaterThanOrEqualTo(minBorrowDate, input);
                predicates.add(predicate);
            }

            if(form.getMaxBorrowDate()!=null){
                Path<LocalDateTime> maxBorrowDate  = root.get("borrowDate");
                LocalDateTime input = form.getMaxBorrowDate().atStartOfDay();
                Predicate predicate = builder.lessThanOrEqualTo(maxBorrowDate, input);
                predicates.add(predicate);
            }

            if(form.getMinReturnDate()!=null){
                Path<LocalDateTime> minReturnDate  = root.get("returnDate");
                LocalDateTime input = form.getMinReturnDate().atStartOfDay();
                Predicate predicate = builder.greaterThanOrEqualTo(minReturnDate, input);
                predicates.add(predicate);
            }

            if(form.getMaxReturnDate()!=null){
                Path<LocalDateTime> maxReturnDate  = root.get("returnDate");
                LocalDateTime input = form.getMaxReturnDate().atStartOfDay();
                Predicate predicate = builder.lessThanOrEqualTo(maxReturnDate, input);
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
