package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/* 1. Todos estes prefixos, de métodos de consulta, funcionam do mesmo jeito do find:
   ### find
   ### query
   ### read
   ### get
   ### stream
   2. Outros prefixos como "count" e "exists":
   ### Verificar se um registro exist (true e false) e count é usado para dar retorno númerico.
   3. Flags são usadas entre o "find" e o "Id" para limitar o resultado da consulta:
   ### First
   ### Top2
   */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurant> findByNomeContainingAndKitchenId(String nome, Long kitchen);

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
