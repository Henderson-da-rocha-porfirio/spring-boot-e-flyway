package com.tuyo.tuyofood.infrastructure.repository;

import com.tuyo.tuyofood.domain.entity.City;
import com.tuyo.tuyofood.domain.entity.State;
import com.tuyo.tuyofood.domain.repository.CityRepository;
import com.tuyo.tuyofood.domain.repository.StateRepository;
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
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> listar() {
        return manager.createQuery("from State", State.class)
                .getResultList();
    }

    @Override
    public State buscar(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public State salvar(State state) {
        return manager.merge(state);
    }

    @Transactional
    @Override
    public void remover(State state) {
        state = buscar(state.getId());
        manager.remove(state);
    }
}
