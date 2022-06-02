package com.tuyo.tuyofood.domain.service;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.exception.EntidadeNaoEncontradaException;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import com.tuyo.tuyofood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant salvar(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.buscar(kitchenId);

        if (kitchen == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("There is no kitchen registration with code %d", kitchenId));
        }

        restaurant.setKitchen(kitchen);

        return restaurantRepository.salvar(restaurant);
    }
}
