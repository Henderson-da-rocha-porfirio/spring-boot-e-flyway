package com.tuyo.tuyofood.infrastructure.repository;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/* 1. EmptyResultDataAccessException(1) = excessão do Spring que é lançada quando
* tentamos devolver alguma coisa e essa coisa não existe.
*  2. EmptyResultDataAccessException(1) = No parâmetro a gente passa o tamanho
* esperado. Neste caso, eu espero pelo menos uma kitchen (1). */
@Component
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> listar() {
        return manager.createQuery("from Kitchen", Kitchen.class)
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
