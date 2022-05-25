package com.tuyo.tuyofood.api.controller;

import com.tuyo.tuyofood.api.model.KitchensXmlWrapper;
import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listarXml() {
        return new KitchensXmlWrapper(kitchenRepository.listar());
    }

    @GetMapping("/{kitchenId}")
    public Kitchen buscar(@PathVariable("kitchenId") Long kitchenId) {
        return kitchenRepository.buscar(kitchenId);
    }
}
