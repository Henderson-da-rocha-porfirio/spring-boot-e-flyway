package com.tuyo.tuyofood.infrastructure.repository;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import com.tuyo.tuyofood.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> listar() {
        return manager.createQuery("from Restaurant", Restaurant.class)
                .getResultList();
    }

    @Override
    public Restaurant buscar(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public Restaurant salvar(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Transactional
    @Override
    public void remover(Restaurant restaurant) {
        restaurant = buscar(restaurant.getId());
        manager.remove(restaurant);
    }
}
