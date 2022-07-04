package com.tuyo.tuyofood.infrastructure.repository.specexemplosembuilder;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

/* 1. ZERO: representa 0 em BigDecimal */

public class RestaurantWithFreteGratisSpec implements Specification<Restaurant> {

    private static final long serialVersionUID = 1L;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
}
