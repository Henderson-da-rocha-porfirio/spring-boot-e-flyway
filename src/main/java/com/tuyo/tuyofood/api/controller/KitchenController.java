package com.tuyo.tuyofood.api.controller;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    /* Listando Coleções de Cozinha */
    @GetMapping
    public List<Kitchen> listar() {
        return kitchenRepository.listar();
    }

    /* * Buscando Uma (singleton) Cozinha específica
       * @PathVariable = passagem da variável onde queremos realizar o Bind (ligação).Tipo: /kitchens/{kitchenId}
       *
       * Método comum para a realização do Bind:

    @GetMapping("/{kitchenId}")
    public Kitchen buscar(@PathVariable("kitchenId") Long id) {
        return kitchenRepository.buscar(id);
    }*/

    /* Com Bind Automático */
    @GetMapping("/{kitchenId}")
    public Kitchen buscar(@PathVariable("kitchenId") Long kitchenId) {
        return kitchenRepository.buscar(kitchenId);
    }
}
