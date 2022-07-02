package com.tuyo.tuyofood.infrastructure.repositoryimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/* */

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String nome,
                                 BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        criteria.from(Restaurant.class);

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();
    }

}
