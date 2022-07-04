package com.tuyo.tuyofood.infrastructure.repository.specexemplosembuilder;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/* @AllArgsConstructor: uma anotação do Lombok que cria os construtores. */

@AllArgsConstructor
public class RestaurantWithNomeSemelhanteSpec implements Specification<Restaurant> {

    private static final long serialVersionUID = 1L;

    private String nome;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        return builder.like(root.get("nome"), "%" + nome + "%");
    }
}
