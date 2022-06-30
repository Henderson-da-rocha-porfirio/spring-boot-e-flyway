package com.tuyo.tuyofood.api.controller;


import com.tuyo.tuyofood.domain.entity.City;
import com.tuyo.tuyofood.domain.exception.EntidadeEmUsoException;
import com.tuyo.tuyofood.domain.exception.EntidadeNaoEncontradaException;
import com.tuyo.tuyofood.domain.repository.CityRepository;
import com.tuyo.tuyofood.domain.service.CityRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegisterService;

    @GetMapping
    public List<City> listar() {
        return cityRepository.findAll();
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> buscar(@PathVariable Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);

        if (city.isPresent()) {
            return ResponseEntity.ok(city.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody City city) {
        try {
            city = cityRegisterService.salvar(city);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(city);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cityId,
                                       @RequestBody City city) {
        try {
            // Podemos usar o orElse(null) também, que retorna a instância de cidade
            // dentro do Optional, ou null, caso ele esteja vazio,
            // mas nesse caso, temos a responsabilidade de tomar cuidado com NullPointerException
            City cityAtual = cityRepository.findById(cityId).orElse(null);

            if (cityAtual != null) {
                BeanUtils.copyProperties(city, cityAtual, "id");

                cityAtual = cityRegisterService.salvar(cityAtual);
                return ResponseEntity.ok(cityAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> remover(@PathVariable Long cityId) {
        try {
            cityRegisterService.excluir(cityId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
