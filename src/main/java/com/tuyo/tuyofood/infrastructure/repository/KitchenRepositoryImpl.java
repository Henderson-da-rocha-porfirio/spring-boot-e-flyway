package com.tuyo.tuyofood.infrastructure.repository;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/* 1. @Repository:
 a. É também um Component.
 b. Ela não é apenas para adicionar semântica, ela tem uma pequena funcionalidade
 que é um tradutor de "exception". Ou seja, ela pode traduzir para outro tipo
 de "exception" para outro tipo específico da própria Spring.
 c. JPQL: "from Kitchen where nome like :nome"
 d. setParameter: está fazendo o bind entre o parâmetro de "nome"(nome que foi passado no parâmetro de JPQL) e nome passado como parâmetro do método(Spring nome).
 */

@Repository
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> listar() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Override
    public List<Kitchen> consultarPorNome(String nome) {
        return manager.createQuery("from Kitchen where nome like :nome", Kitchen.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public Kitchen buscar(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    @Override
    public Kitchen salvar(Kitchen cozinha) {
        return manager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Kitchen kitchen = buscar(id);

        if (kitchen == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(kitchen);
    }
}
