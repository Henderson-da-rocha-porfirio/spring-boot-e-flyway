package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurant> findByNomeContainingAndKitchenId(String nome, Long kitchen);

}
