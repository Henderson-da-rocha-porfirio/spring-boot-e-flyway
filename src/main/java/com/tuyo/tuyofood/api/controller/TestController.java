package com.tuyo.tuyofood.api.controller;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import com.tuyo.tuyofood.domain.repository.RestaurantRepository;
import com.tuyo.tuyofood.infrastructure.repository.specexemplosembuilder.RestaurantWithFreteGratisSpec;
import com.tuyo.tuyofood.infrastructure.repository.specexemplosembuilder.RestaurantWithNomeSemelhanteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.tuyo.tuyofood.infrastructure.repository.spec.RestaurantSpecs.withFreteGratis;
import static com.tuyo.tuyofood.infrastructure.repository.spec.RestaurantSpecs.withNomeSemelhante;

/* 1. @RequestParam: Busca o parâmetro da requisição.
    1.1: @RequestParam("nomeCozinha") = Usado se quiser modificar o nome do parâmetro mudado.
*  2. @PathVariable: Busca pelo caminho e o parâmetro em "{ }"
   3. Containing: é uma flag que coloca o % e o Like antes e depois.
   4. Endpoints: /kitchens/por-nome
   5. Spec: vem de Specification do DDD
   6. Filtros no Spec: fica por parte de quem está usando os filtros, e não por parte do repositório em si,
mas através dos métodos implementados. O problema disso é ficar dependente dos métodos por criar muita
duplicidade de código.
   */

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/kitchens/por-nome")
    public List<Kitchen> kitchensPorNome(String nome) {
        return kitchenRepository.findTodasByNomeContaining(nome);
    }

    @GetMapping("/kitchens/unica-por-nome")
    public Optional<Kitchen> kitchenPorNome(String nome) {
        return kitchenRepository.findByNome(nome);
    }


    @GetMapping("/kitchens/exists")
    public boolean kitchenExists(String nome) {
        return kitchenRepository.existsByNome(nome);
    }

    @GetMapping("/kitchens/primeira")
    public Optional<Kitchen> kitchenPrimeiro() {
        return kitchenRepository.buscarPrimeiro();
    }

    @GetMapping("/restaurants/por-taxa-frete")
    public List<Restaurant> restaurantsPorTaxaFrete(
            BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restaurantRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurants/por-nome")
    public List<Restaurant> restaurantsPorTaxaFrete(
            String nome, Long kitchenId) {
        return restaurantRepository.consultarPorNome(nome, kitchenId);
    }

    @GetMapping("/restaurants/primeiro-por-nome")
    public Optional<Restaurant> restaurantPrimeiroPorNome(String nome) {
        return restaurantRepository.findFirstRestaurantByNomeContaining(nome);
    }

    @GetMapping("/restaurants/top2-por-nome")
    public List<Restaurant> restaurantsTop2PorNome(String nome) {
        return restaurantRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurants/count-por-kitchen")
    public int restaurantsCountPorKitchen(Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    @GetMapping("/restaurants/por-nome-e-frete")
    public List<Restaurant> restaurantPorNomeFrete(String nome,
                                                   BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restaurantRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }


    @GetMapping("/restaurants/with-frete-gratis")
    public List<Restaurant> restaurantsComFreteGratis(String nome) {
        return restaurantRepository.findWithFreteGratis(nome);
    }

    @GetMapping("/restaurants/primeiro")
    public Optional<Restaurant> restaurantPrimeiro() {
        return restaurantRepository.buscarPrimeiro();
    }

    /* @GetMapping("/restaurants/with-frete-gratis")
    public List<Restaurant> restaurantsWithFreteGratis(String nome) {

        return restaurantRepository.findAll(withFreteGratis()
                .and(withNomeSemelhante(nome)));
    } */

    // Exemplo de Specification Sem o Builder:
   /* @GetMapping("/restaurants/with-frete-gratis")
    public List<Restaurant> restaurantesWithFreteGratis(String nome) {
        var withFreteGratis = new RestaurantWithFreteGratisSpec();
        var withNomeSemelhante = new RestaurantWithNomeSemelhanteSpec(nome);

        return restaurantRepository.findAll(withFreteGratis.and(withNomeSemelhante));
    }*/

    // Exemplo de Specification apenas com nomes semelhantes:
    /*@GetMapping("/restaurants/with-frete-gratis")
    public List<Restaurant> restaurantesWithFreteGratis(String nome) {
        var withFreteGratis = new RestaurantWithFreteGratisSpec();
        var withNomeSemelhante = new RestaurantWithNomeSemelhanteSpec(nome);

        return restaurantRepository.findAll(withNomeSemelhante);
    }*/

    /* Recebendo por Query String: */
 /*   @GetMapping("/kitchens/por-nome")
    public List<Kitchen> kitchensPorNome(@RequestParam("nome") String nome) {
        return kitchenRepository.consultarPorNome(nome);
    }*/

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

        /*@GetMapping("/restaurants/por-nome")
    public List<Restaurant> restaurantsPorTaxaFrete(
            String nome, Long kitchenId) {
        return restaurantRepository.findByNomeContainingAndKitchenId(nome, kitchenId);
    }
    */
}
