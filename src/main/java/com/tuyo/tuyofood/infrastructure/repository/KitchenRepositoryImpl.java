package com.tuyo.tuyofood.infrastructure.repository;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/* 1. Esta classe possui uma implementação técnica. Por isso não está dentro de Domain.
*  2. É uma classe que contém a técnica de como acessar ao Database.
*  3. Esta classe não tem nada a ver com a nossa camada de regra de negócio.
*  4. Faz parte de uma camada de infraestrutura que está dentro de um pacote de infraestrutura.
*  5. Sendo um pacote de infraestrutura, podemos colocar as implementações que não tenham a ver com o negócio.
*  Por exemplo, envio de email, ou seja, um tipo de conexão diferente. */
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
    public void remover(Kitchen cozinha) {
        cozinha = buscar(cozinha.getId());
        manager.remove(cozinha);
    }
}
