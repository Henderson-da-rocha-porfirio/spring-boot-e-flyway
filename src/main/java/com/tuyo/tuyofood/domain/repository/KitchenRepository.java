package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* 1. findBy (buscar por): O Spring Data JPA entende que esse prefixo, que é um delimitador, a partir do "by" começam os critérios.
*  2. findBy: entre o "find" e o "by" pode ser colocada qualquer coisa. Exemplo: findTodasByNome  */
@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    List<Kitchen> findTodasByNomeContaining(String nome);

    Optional<Kitchen> findByNome(String nome);

}
