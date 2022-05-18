package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.entity.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> listar();
    Restaurant buscar(Long id);
    Restaurant salvar(Restaurant restaurant);
    void remover(Restaurant restaurant);
}
