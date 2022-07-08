package com.tuyo.tuyofood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.exception.EntidadeNaoEncontradaException;
import com.tuyo.tuyofood.domain.repository.RestaurantRepository;
import com.tuyo.tuyofood.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* 1. ObjectMapper: converte objetos json em java e vice-versa
*  2. paymentForms: é adicionado para que as outras formas não sejam deletadas sem necessidade. */

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @GetMapping
    public List<Restaurant> listar() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> buscar(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get());
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

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restaurantId,
                                       @RequestBody Restaurant restaurant) {
        try {
            Restaurant restaurantAtual = restaurantRepository.findById(restaurantId).orElse(null);

            if (restaurantAtual != null) {
                BeanUtils.copyProperties(restaurant, restaurantAtual, "id", "paymentForms");

                restaurantAtual = restaurantRegisterService.salvar(restaurantAtual);
                return ResponseEntity.ok(restaurantAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restaurantId,
                                              @RequestBody Map<String, Object> campos) {
        Restaurant restaurantAtual = restaurantRepository.findById(restaurantId).orElse(null);

        if (restaurantAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restaurantAtual);

        return atualizar(restaurantId, restaurantAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurant restaurantDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantOrigem = objectMapper.convertValue(dadosOrigem, Restaurant.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restaurantOrigem);

//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restaurantDestino, novoValor);
        });
    }
}
