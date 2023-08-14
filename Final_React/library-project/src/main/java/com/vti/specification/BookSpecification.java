package com.vti.specification;

import com.vti.entity.Book;
import com.vti.form.BookFilterForm;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BookSpecification {
    public static Specification<Book> buildSpec(BookFilterForm form) {
        return (root, query, builder) -> {
            if (form == null) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasText(form.getBookTitleAndAuthor())){
                String pattern = "%" + form.getBookTitleAndAuthor().trim() + "%";
                Path<String> title = root.get("title");
                Predicate hasTitleLike = builder.like(title, pattern);
                Path<String> author = root.get("author");
                Predicate hasAuthorLike = builder.like(author, pattern);
                predicates.add(builder.or(hasTitleLike, hasAuthorLike));
            }

            if(StringUtils.hasText(form.getBookCategory())){
                String pattern = "%" + form.getBookCategory().trim() + "%";
                Path<String> category = root.get("category");
                Predicate hasCategoryLike = builder.like(category, pattern);
                predicates.add(hasCategoryLike);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
