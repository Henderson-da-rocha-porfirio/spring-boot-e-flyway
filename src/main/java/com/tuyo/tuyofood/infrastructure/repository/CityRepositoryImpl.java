package com.tuyo.tuyofood.infrastructure.repository;

import com.tuyo.tuyofood.domain.entity.City;
import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.CityRepository;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> listar() {
        return manager.createQuery("from City", City.class)
                .getResultList();
    }

    @Override
    public City buscar(Long id) {
        return manager.find(City.class, id);
    }

    @Transactional
    @Override
    public City salvar(City city) {
        return manager.merge(city);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        City city = buscar(id);

        if (city == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(city);
    }
}
