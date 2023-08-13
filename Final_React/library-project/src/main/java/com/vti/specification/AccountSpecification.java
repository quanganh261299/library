package com.vti.specification;

import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AccountSpecification {
    public static Specification<Account> buildSpec(AccountFilterForm form) {
        return (root, query, builder) -> {
            if (form == null) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();

//          Filter account by username
            if (StringUtils.hasText(form.getName())) {
                String pattern = "%" + form.getName().trim() + "%";
                Path<String> username = root.get("username");
                Predicate predicate = builder.like(username, pattern);
                predicates.add(predicate);
            }

//            Filter account by Ids
            if(form.getMinId() != null){
                Path<Integer> id = root.get("id");
                Predicate predicate = builder.greaterThanOrEqualTo(id, form.getMinId()); // WHERE Id >= ?
                predicates.add(predicate);
            }
            if(form.getMaxId() != null){
                Path<Integer> id = root.get("id");
                Predicate predicate = builder.lessThanOrEqualTo(id, form.getMaxId()); // WHERE Id <= ?
                predicates.add(predicate);
            }

//            Filter account by role
            if(form.getRole() != null){
                Path<Account.Role> role = root.get("role");
                Predicate predicate = builder.equal(role, form.getRole());
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
