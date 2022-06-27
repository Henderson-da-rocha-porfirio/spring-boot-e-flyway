package com.tuyo.tuyofood.api.controller;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* 1. @RequestParam: Busca o parâmetro da requisição.
    1.1: @RequestParam("nomeCozinha") = Usado se quiser modificar o nome do parâmetro mudado.
*  2. @PathVariable: Busca pelo caminho e o parâmetro em "{ }" */

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    /* Recebendo por Query String: */
    @GetMapping("/kitchens/por-nome")
    public List<Kitchen> kitchensPorNome(@RequestParam("nome") String nome) {
        return kitchenRepository.consultarPorNome(nome);
    }

    /*

    @GetMapping("/kitchens/por-nome")
    public List<Kitchen> kitchensPorNome(@RequestParam String nome) {
        return kitchenRepository.consultarPorNome(nome);
    }

    ou

     @GetMapping("/kitchens/por-nome")
    public List<Kitchen> kitchensPorNome(@RequestParam("nomeCozinha") String nome) {
        return kitchenRepository.consultarPorNome(nome);
    }
    */

    /* Recebendo por caminho(path) e passando o nome na URI:
    @GetMapping("/kitchens/por-nome/{nome}")
    public List<Kitchen> kitchensPorNome(@PathVariable String nome) {
        return kitchenRepository.consultarPorNome(nome);
    }
    */
}
