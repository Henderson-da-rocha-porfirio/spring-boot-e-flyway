package com.tuyo.tuyofood.api.controller;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.exception.EntidadeNaoEncontradaException;
import com.tuyo.tuyofood.domain.repository.RestaurantRepository;
import com.tuyo.tuyofood.domain.service.RestaurantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @GetMapping
    public List<Restaurant> listar() {
        return restaurantRepository.listar();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> buscar(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRepository.buscar(restaurantId);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantRegisterService.salvar(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}
