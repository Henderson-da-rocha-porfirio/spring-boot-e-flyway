package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Kitchen;

import java.util.List;

/* 1. Uma Interface Repository ( Repositório Orientado a persistência ):
*  a. Pertence ao Domínio da Aplicação:
*  b. Não deve ter muitos detalhes técnicos
*  c. Não informa nada sobre o mecanismo de persistência dos dados
*  d. Por isso é colocada dentro de Domain
*  e. É preciso criar uma classe para implementar esta classe.
*  f. Por padrão, ele deve imitar uma Coleção: add, get e etc...
*  g. Não se cria um repositório por tabela e/ou por entidade.
*  h. Um repository é por agregado (conjunto de agregado/aggregate)
*  i. Não criar repositórios para entidades que não são "aggregate root".
*  2. Pensando na Camada de Negócio de um Delivery de Comida: o que um repositório de cozinha tem que ter:
*  a. Listar Cozinhas
*  b. Buscar Cozinhas pelo ID
*  c. Salvar uma Cozinha
*  d. Remover uma Cozinha
*  e. Poderia ter outros métodos específicos */
public interface KitchenRepository {

    List<Kitchen> listar();
    Kitchen buscar(Long id);
    Kitchen salvar(Kitchen kitchen);
    void remover(Kitchen kitchen);
}
