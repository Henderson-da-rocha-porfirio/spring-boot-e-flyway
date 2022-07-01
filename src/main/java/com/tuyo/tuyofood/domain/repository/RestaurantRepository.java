package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/* 1. nome: passado no parâmetro deve fazer o bind com %:nome%
*  2. @Param("id"): faz o bind de kitchen passado no parâmetro com :id
*  3. @Query: torna possível o uso do JPQL
*  4. Consultas:
*  a. @Query: usando JPQL
*  b. Find: usando prefixos e flags
*  c. Externas: META-INF/orm.xml   */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//    @Query("from Restaurant where nome like %:nome% and kitchen.id = :id")
    List<Restaurant> consultarPorNome(String nome, @Param("id") Long kitchen);

//    List<Restaurant> findByNomeContainingAndKitchenId(String nome, Long kitchen);

    Optional<Restaurant> findFirstRestaurantByNomeContaining(String nome);

    List<Restaurant> findTop2ByNomeContaining(String nome);

    int countByKitchenId(Long cozinha);

    /* EXEMPLOS: */

    /*List<Restaurant> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurant> queryByNomeContainingAndKitchenId(String nome, Long kitchen);*/

    /*List<Restaurant> readByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurant> readByNomeContainingAndKitchenId(String nome, Long kitchen);*/

    /*List<Restaurant> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurant> getByNomeContainingAndKitchenId(String nome, Long kitchen);*/

    /*List<Restaurant> streamByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurant> streamByNomeContainingAndKitchenId(String nome, Long kitchen);*/
}
