package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Restaurant;

import java.math.BigDecimal;
import java.util.List;


public interface RestaurantRepositoryQueries {

    List<Restaurant> find(String nome,
                          BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}
