package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository
        extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant> {

    @Query("from Restaurant r join fetch r.kitchen")
    List<Restaurant> findAll();

    List<Restaurant> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    //	@Query("from Restaurant where nome like %:nome% and kitchen.id = :id")
    List<Restaurant> consultarPorNome(String nome, @Param("id") Long kitchen);

//	List<Restaurant> findByNomeContainingAndKitchenId(String nome, Long kitchen);

    Optional<Restaurant> findFirstRestaurantByNomeContaining(String nome);

    List<Restaurant> findTop2ByNomeContaining(String nome);

    int countByKitchenId(Long kitchen);
}
