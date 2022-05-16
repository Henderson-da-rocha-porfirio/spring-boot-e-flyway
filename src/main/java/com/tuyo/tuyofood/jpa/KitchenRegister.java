package com.tuyo.tuyofood.jpa;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRegister {

    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> listar() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    public Kitchen buscar(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    public Kitchen salvar(Kitchen kitchen) {
        return manager.merge(kitchen);
    }
}

