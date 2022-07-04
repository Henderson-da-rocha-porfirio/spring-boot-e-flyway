package com.tuyo.tuyofood.infrastructure.repository.spec;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/* 1. Lambda: " -> " */

public class RestaurantSpecs {

    public static Specification<Restaurant> withFreteGratis() {
        return (root, query, builder) ->
                builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withNomeSemelhante(String nome) {
        return (root, query, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%");
    }
}
