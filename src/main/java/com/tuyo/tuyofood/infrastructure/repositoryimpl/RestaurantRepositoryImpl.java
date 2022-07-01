package com.tuyo.tuyofood.infrastructure.repositoryimpl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;

/* 1. var: recurso do java 10.
*  2. @Query("from Restaurant where nome like %:nome% and kitchen.id = :id"):
*  a. %:nome%: pode ser usado com JPQL dentro de @Query mas não no JPQL com var jpql
* */

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String nome,
                                 BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        var jpql = "from Restaurant where nome like :nome "
                + "and taxaFrete between :taxaInicial and :taxaFinal";

        return manager.createQuery(jpql, Restaurant.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaFreteInicial)
                .setParameter("taxaFinal", taxaFreteFinal)
                .getResultList();
    }

}
